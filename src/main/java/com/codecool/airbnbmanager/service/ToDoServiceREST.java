package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceREST {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    LodgingsRepository lodgingsRepository;


    public boolean addNewTodo(long lodgingsId, ToDo todo) {
        Lodgings lodgings = lodgingsRepository.findById(lodgingsId).orElse(null);
        if(lodgings==null) return false;
        todo.setLodgings(lodgings);

        toDoRepository.save(todo);

        return true;

    }

    public boolean deleteTodo(long id) {
        ToDo toDo = toDoRepository.findById(id);
        if(toDo==null) return false;

        toDoRepository.delete(toDo);
        return true;
    }
}
