package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.service.LodgingsServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LodgingsControllerREST {

    @Autowired
    LodgingsServiceREST lodgingsServiceREST;

    private List<String> fieldsToExclude = new ArrayList<>();

//    @PostMapping(value = "/api/login")
//    public String login(@RequestParam String email, @RequestParam String passw, HttpSession session) {
//        // kikeresem a DBbol
//        session.setAttribute("userId", "dsgfdfdg");
//        return null;
//    }

    @GetMapping(value = "/api/lodgings2") //just for demo
    public String lodgingsListDemo() {
        return lodgingsServiceREST.listAllLodgingsByUser("akincsei@gmail.com");
    }

    @GetMapping(value = "/api/lodgings") //todo: session attribute real key!!!
    public String lodgingsList(@SessionAttribute("landlord") String userEmail, HttpSession session) {
        String email = (String) session.getAttribute("emailAddress");
        return lodgingsServiceREST.listAllLodgingsByUser(userEmail);
    }

    @GetMapping(value = "/api/lodgings/{lodgingsId}")
    public String lodgingDetails(@PathVariable String lodgingsId) {
        return lodgingsServiceREST.createJsonString(Long.parseLong(lodgingsId), fieldsToExclude);
    }

    @GetMapping(value = "/api/lodgings/edit/{lodgingsId}")
    public String getLodgingEdit(@PathVariable String lodgingsId) {
        return lodgingsServiceREST.createJsonString(Long.parseLong(lodgingsId), fieldsToExclude);
    }

    @PutMapping(value = "/api/lodgings/edit/{lodgingsId}", consumes = "text/plain")
    public String postLodgingEdit(@RequestBody String body) {
        boolean isUpdateSuccessFul = lodgingsServiceREST.handleLodgingsUpdate(body);
        return (isUpdateSuccessFul) ? "SUCCESS" : "FAIL";
    }

    @DeleteMapping(value = "/api/lodgings/delete/{lodgingsId}")
    @Transactional
    public String deleteLodgings(@PathVariable String lodgingsId) {
        boolean isDeletionSuccessful = lodgingsServiceREST.handleLodgingsDeletion(Long.parseLong(lodgingsId));
        return (isDeletionSuccessful) ? "SUCCESS" : "FAIL";
    }

    @PostMapping(value = "/api/lodgings/add") //todo: session attribute real key!!!
    public String addLodgings(@RequestBody String body, @SessionAttribute("landlord") String landlordEmail) {
        boolean isAddSuccessful = lodgingsServiceREST.handleAddNewLodgings(body, landlordEmail);
        return (isAddSuccessful) ? "SUCCESS" : "FAIL";
    }

    @PostMapping(path = "/api/lodgings-add-todo/{id}", consumes = "application/json", produces = "application/json")
    public Lodgings addToDoToLodgings(@PathVariable(name = "id") Long id, @RequestBody ToDo body) {
        return lodgingsServiceREST.handleAddTodoToLodgings(id, body);
    }

}
