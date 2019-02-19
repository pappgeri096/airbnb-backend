package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.service.ToDoServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoControllerREST {

    @Autowired
    private ToDoServiceREST toDoService;
}