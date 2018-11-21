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


    public void handleToDoSaving(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    public String getAllToDosByLodgingsId(Long lodgingsId) {

        List<ToDo> toDoList = new ArrayList<>(toDoRepository.findAllByLodgingsId(lodgingsId));

        return JsonMappingHandler.mapJavaObjectListToJsonArray(toDoList);
    }

    public String createToDoJsonStringById(Long id) {
        ToDo mightBeToDo = toDoRepository.findById(id).orElse(null);

        String returnString = "FAIL";

        if (mightBeToDo == null) {
            return returnString;
        }

        try {
            returnString = JsonMappingHandler.mapJavaObjectWithoutFields(mightBeToDo, Collections.emptyList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    public boolean handleToDoUpdate(String todoData) {

        boolean isUpdateSuccessful = false;

        Map<String, String> map = JsonMappingHandler.convertJsonArraytoMap(todoData);

        if (map.isEmpty()) {
            System.out.println("map is empty");
            return isUpdateSuccessful;

        }

        long toDoId = Long.parseLong(map.get(ToDoFieldType.ID.getInputString()));
        ToDo toDoToUpdate = toDoRepository.findById(toDoId).orElse(null);

        if (toDoToUpdate == null) {
            System.out.println("todo is not found with ID: " + toDoId);
            return isUpdateSuccessful;
        }

        toDoToUpdate.setName(map.get(ToDoFieldType.NAME.getInputString()));

        String deadline = map.get(ToDoFieldType.DEADLINE.getInputString());
        Date date = DateFormatConverter.convertStringToDate(deadline);
        toDoToUpdate.setDeadline(date);

        toDoToUpdate.setDescription(map.get(ToDoFieldType.DESCRIPTION.getInputString()));
        toDoToUpdate.setPrice(Long.parseLong(map.get(ToDoFieldType.PRICE.getInputString())));
        toDoToUpdate.setStatus(ToDoStatusType.valueOf(map.get(ToDoFieldType.STATUS.getInputString()).toUpperCase()));
        boolean obsolete = map.get(ToDoFieldType.OBSOLETE.getInputString()).equals("true");
        toDoToUpdate.setObsolete(obsolete);

        toDoRepository.save(toDoToUpdate);
        isUpdateSuccessful = true;

        return isUpdateSuccessful;
    }

    public boolean handleToDoDeletionBy(Long id) {
        ToDo todo = toDoRepository.findById(id).orElse(null);
        if (todo == null) {
            return false;
        }
        toDoRepository.delete(todo);
        return true;
    }

    public boolean handleToDoAddition(String body) {

        Map<String, String> map = JsonMappingHandler.convertJsonArraytoMap(body);

        String lodgingsIdString = map.get(ToDoFieldType.LODGINGS_ID.getInputString());
        Long lodgingsId = Long.parseLong(lodgingsIdString);
        Lodgings lodgings = lodgingsServiceREST.handleFindById(lodgingsId);

        if (lodgings == null) {
            return false;
        }

        ToDo toDo = new ToDo();

        toDo.setName(map.get(ToDoFieldType.NAME.getInputString()));
        toDo.setLodgings(lodgings);

        String deadline = map.get(ToDoFieldType.DEADLINE.getInputString());
        Date date = DateFormatConverter.convertStringToDate(deadline);
        toDo.setDeadline(date);

        toDo.setDescription(map.get(ToDoFieldType.DESCRIPTION.getInputString()));
        toDo.setPrice(Long.parseLong(map.get(ToDoFieldType.PRICE.getInputString())));
        toDo.setStatus(ToDoStatusType.valueOf(map.get(ToDoFieldType.STATUS.getInputString()).toUpperCase()));

        boolean obsolete = map.get(ToDoFieldType.OBSOLETE.getInputString()).equals("true");
        toDo.setObsolete(obsolete);

        toDoRepository.save(toDo);
        return true;
    }

}
