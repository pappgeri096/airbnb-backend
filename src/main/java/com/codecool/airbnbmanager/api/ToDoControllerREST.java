package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.service.ToDoServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoControllerREST extends HttpServlet {

    @Autowired
    private ToDoServiceREST toDoService;

    @GetMapping(path = "/api/todos")
    public List<ToDo> toDoViewAll() {
        return toDoService.getAllToDos();
    }


    @GetMapping(path = "/api/all-todos-by-lodgings-id/{id}")
    public String toDoViewAllByLodgings(@PathVariable(name = "id") Long id) {
        return toDoService.getAllToDosByLodgingsId(id);
    }

    @GetMapping(path = {"/api/todo/{id}", "/api/todo/edit/{id}"})
    public String toDoViewSingleAndEditGet(@PathVariable(name = "id") Long id) {
        return toDoService.createToDoJsonStringById(id);
    }

    @PutMapping(path = "/api/todo/edit", consumes = "text/plain")
    public boolean toDoEditPost(@RequestBody String body) {
        return toDoService.handleToDoUpdate(body);
    }

    @DeleteMapping(path = "/api/todo/delete/{id}")
    public boolean todoDeletion(@PathVariable(name = "id") Long id) {
        return toDoService.handleToDoDeletionBy(id);

    }

    @PostMapping(path = "/api/todo/add")  // , consumes = "application/json", produces = "application/json"
    public boolean toDoAddition(@RequestBody String body) {
        return toDoService.handleToDoAddition(body);
    }
}