package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import com.codecool.airbnbmanager.util.*;
import com.codecool.airbnbmanager.util.enums.ToDoStatusType;
import com.codecool.airbnbmanager.util.enums.ToDoFieldType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ToDoServiceREST {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    LodgingsServiceREST lodgingsServiceREST;


}
