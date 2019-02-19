package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceREST {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    LodgingsServiceREST lodgingsServiceREST;


}
