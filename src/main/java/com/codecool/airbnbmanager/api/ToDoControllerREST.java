package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.service.ToDoServiceREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;

import static com.codecool.airbnbmanager.configuration.Initializer.FAIL_MESSAGE;
import static com.codecool.airbnbmanager.configuration.Initializer.SUCCESS_MESSAGE;

@RestController
public class ToDoControllerREST extends HttpServlet {

    @Autowired
    private ToDoServiceREST toDoService;

    @GetMapping(path = "/api/all-todos-by-lodgings-id/{id}")
    public String toDoViewAll(@PathVariable(name = "id") Long id) {
        return toDoService.getAllToDosByLodgingsId(id);
    }

    @GetMapping(path = {"/api/todo/{id}", "/api/todo/edit/{id}"})
    public String toDoViewSingleAndEditGet(@PathVariable(name = "id") Long id) {
        return toDoService.createToDoJsonStringById(id);
    }

    @PutMapping(path = "/api/todo/edit", consumes = "text/plain")
    public String toDoEditPost(@RequestBody String body) {
        boolean isUpdateSuccessful = toDoService.handleToDoUpdate(body);
        return (isUpdateSuccessful) ? SUCCESS_MESSAGE : FAIL_MESSAGE;
    }

    @DeleteMapping(path = "/api/todo/delete/{id}")
    public String todoDeletion(@PathVariable(name = "id") Long id) {
        boolean isDeletionSuccessful = toDoService.handleToDoDeletionBy(id);
        return (isDeletionSuccessful) ? SUCCESS_MESSAGE : FAIL_MESSAGE;

    }

    @PostMapping(path = "/api/todo/add")
    public String toDoAddition(@RequestBody String body) {
        boolean isAdditionSuccessful = toDoService.handleToDoAddition(body);
        return (isAdditionSuccessful) ? SUCCESS_MESSAGE : FAIL_MESSAGE;

    }
}