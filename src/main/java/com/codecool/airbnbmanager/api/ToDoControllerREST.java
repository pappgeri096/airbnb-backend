package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.exceptions.LodgingsNotFoundException;
import com.codecool.airbnbmanager.exceptions.ToDoNotFoundException;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import com.codecool.airbnbmanager.util.enums.ToDoStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        Lodgings lodgings = lodgingsRepository.findById(lodgingsId)
                .orElseThrow(() -> new LodgingsNotFoundException(lodgingsId));
        toDo.setLodgings(lodgings);
        return toDoRepository.save(toDo);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public ResponseEntity<Boolean> addNewTodo(@PathVariable("id") long id){
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException(id));
        toDoRepository.delete(toDo);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public ResponseEntity<Boolean> markTodo(@PathVariable("id") long id, @RequestBody ToDo todo){
        ToDo currentTodo = toDoRepository.findById(todo.getId())
                .orElseThrow(() -> new ToDoNotFoundException(todo.getId()) );
        currentTodo.setStatus(ToDoStatusType.DONE);
        toDoRepository.save(currentTodo);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }
}