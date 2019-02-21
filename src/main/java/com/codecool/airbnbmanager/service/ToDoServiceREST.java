package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceREST {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    LodgingsServiceREST lodgingsServiceREST;


    public boolean addNewTodo(long lodgingsId, ToDo todo) {
        Lodgings lodgings = lodgingsServiceREST.getLodgingsById(lodgingsId);
        if(lodgings==null) return false;
        todo.setLodgings(lodgings);

        toDoRepository.save(todo);

        return true;

    }
}
