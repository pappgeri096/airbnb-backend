package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.message.response.ResponseMessage;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.service.ToDoServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todos")
public class ToDoControllerREST {

    @Autowired
    private ToDoServiceREST toDoService;

    @PostMapping("/{lodgingsId}/add")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public ResponseEntity<?> addNewTodo(@PathVariable("lodgingsId") long lodgingsId, @RequestBody ToDo toDo){

        if(!toDoService.addNewTodo(lodgingsId, toDo)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseMessage("ADDED"), HttpStatus.OK);

    }
}