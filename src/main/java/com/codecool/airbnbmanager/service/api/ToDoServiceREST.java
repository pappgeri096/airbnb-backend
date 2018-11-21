package com.codecool.airbnbmanager.service.api;

import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import com.codecool.airbnbmanager.util.DateFormatConverter;
import com.codecool.airbnbmanager.util.JsonMappingHandler;
import com.codecool.airbnbmanager.util.Status;
import com.codecool.airbnbmanager.util.ToDoFieldType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public String getAllToDosByLodgingsId(String body) {
        Long lodgingsId = lodgingsServiceREST.validateRequestByLodgingsId(body);

        if (lodgingsId == null) {
            return "FAIL";
        }

        List<ToDo> toDoList = new ArrayList<>(toDoRepository.findAllByLodgingsId(lodgingsId));

        return JsonMappingHandler.writeListToJsonString(toDoList);
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

        Long toDoId = Long.parseLong(map.get(ToDoFieldType.ID.getInputString()));
        ToDo toDoToUpdate = toDoRepository.findById(toDoId).orElse(null);

        if (toDoToUpdate == null) {
            System.out.println("todo is not found with ID: " + toDoId);
            return isUpdateSuccessful;
        }

        toDoToUpdate.setName(map.get(ToDoFieldType.NAME.getInputString()));

        String deadline = map.get(ToDoFieldType.DEADLINE.getInputString());
        Date date = DateFormatConverter.convertTimeStampToDate(deadline);
        toDoToUpdate.setDeadline(date);

        toDoToUpdate.setDescription(map.get(ToDoFieldType.DESCRIPTION.getInputString()));
        toDoToUpdate.setPrice(Long.parseLong(map.get(ToDoFieldType.PRICE.getInputString())));
        toDoToUpdate.setStatus(Status.valueOf(map.get(ToDoFieldType.STATUS.getInputString()).toUpperCase()));
        boolean obsolete = map.get(ToDoFieldType.OBSOLETE.getInputString()).equals("true");
        toDoToUpdate.setObsolete(obsolete);

        toDoRepository.save(toDoToUpdate);
        isUpdateSuccessful = true;

        return isUpdateSuccessful;
    }



//    @Override
//    public void handleAddPost(ToDo toDo) {
//        this.toDoDao.add(toDo);
//    }
//
//    @Override
//    public User handleGetUserBy(String userEmail) {
//        return userHandler.handleGetUserBy(userEmail);
//    }
//
//    @Override
//    public List<ToDo> handleGetAllBy(long id) {
//        return null;
//    }
//
//
//    @Override
//    public void handleDeletion(long id) {
//
//    }
//
//    @Override
//    public String handleCrudGetBy(String requestPath, String id) {
//        String templateToRender;
//        switch (requestPath) {
//            case "/todo":
//                templateToRender = "todos.html";
//                break;
//            case "/todo/add":
//                templateToRender = "add_todo.html";
//                break;
//            case "/todo/edit":
//                templateToRender = "edit_todo.html";
//                break;
//            case "/todo/delete":
//                templateToRender = null;
//                break;
//            default:
//                templateToRender = "todos.html";
//                break;
//        }
//        return templateToRender;
//    }
//
//    @Override
//    public List<String> getEnumAsStringList() {
//        return null;
//    }
//
//    public List<ToDo> handleGetAllTodosBy(List<Lodgings> lodgingsList) {
//        return toDoDao.getAllTodosBy(lodgingsList);
//    }
}
