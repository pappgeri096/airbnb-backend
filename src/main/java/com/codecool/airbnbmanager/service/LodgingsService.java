package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.service.api.ToDoServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LodgingsService {

    @Autowired
    private LodgingsRepository lodgingsRepository;

    @Autowired
    private ToDoServiceREST toDoServiceREST;


    public void handleLodgingsAddition(Lodgings lodgings) {
        lodgingsRepository.save(lodgings);
    }

    public List<Lodgings> findAllLodgingsByUser(User user) {// todo: handles only landlords
        return lodgingsRepository.findAllByLandlord(user);
    }

    public boolean handleAddTodoToLodgings(Lodgings lodgings, ToDo toDo) {
        if (lodgingsRepository.findById(lodgings.getId()).isPresent()) {
            toDo.setLodgings(lodgings);
            toDoServiceREST.handleToDoSaving(toDo);
            return true;
        }
        return false;
    }
}
