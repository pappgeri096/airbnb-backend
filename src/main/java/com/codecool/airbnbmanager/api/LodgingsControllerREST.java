package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.message.response.ResponseMessage;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsServiceREST;
import com.codecool.airbnbmanager.service.UserServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lodgings")
public class LodgingsControllerREST {

    @Autowired
    LodgingsServiceREST lodgingsServiceREST;

    @Autowired
    UserServiceREST userServiceREST;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('PROPERTY') OR hasRole('LANDLORD')")
    public Lodgings getLodgingsById(@PathVariable("id") long id){
        return lodgingsServiceREST.getLodgingsById(id);
    }

    @PostMapping("/{username}/add")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<?> addNewLodgings(@RequestBody Lodgings lodgings, @PathVariable("username") String username ){

        System.out.println(lodgings.getLodgingsType());
        User user = userServiceREST.getUserByUsername(username);
        lodgings.setLandlord(user);
        lodgingsServiceREST.addNewLodgings(lodgings);
        return new ResponseEntity<>(new ResponseMessage("Lodgings added successfully!"), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<?> updateLodgings(@RequestBody Lodgings lodgings, @PathVariable("id") long id ){

        if(!lodgingsServiceREST.updateLodgings(lodgings, id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseMessage("Lodgings updated successfully!"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<?> deleteLodgings(@PathVariable("id") long id ){
        Lodgings lodgings = lodgingsServiceREST.getLodgingsById(id);
        if(lodgings == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        lodgingsServiceREST.deleteLodgings(id);
        return new ResponseEntity<>(new ResponseMessage("DELETED"), HttpStatus.OK);
    }

}
