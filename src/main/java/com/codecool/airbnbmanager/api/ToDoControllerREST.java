package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todos")
public class ToDoControllerREST {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private LodgingsRepository lodgingsRepository;

    @PostMapping("/{lodgingsId}")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public ToDo addNewTodo(@PathVariable("lodgingsId") long lodgingsId, @RequestBody ToDo toDo){
        Lodgings lodgings = lodgingsRepository.findById(lodgingsId).orElse(null);
        if(lodgings==null) return null;
        toDo.setLodgings(lodgings);
        return toDoRepository.save(toDo);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public void addNewTodo(@PathVariable("id") long id){
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        if(toDo==null) return;
        toDoRepository.delete(toDo);

    }
}