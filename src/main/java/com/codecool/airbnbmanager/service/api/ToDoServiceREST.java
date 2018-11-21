package com.codecool.airbnbmanager.service.api;

import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.repository.ToDoRepository;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.util.FieldType;
import com.codecool.airbnbmanager.util.JsonMappingHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ToDoServiceREST {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    LodgingsService lodgingsService;


    public void handleToDoSaving(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    public String getAllToDosByLodgingsId(String body) {
        Long lodgingsId = lodgingsService.validateRequestByLodgingsId(body);

        if (lodgingsId == null) {
            return "FAIL";
        }

        List<ToDo> toDoList = new ArrayList<>(toDoRepository.findAllByLodgingsId(lodgingsId));

        return JsonMappingHandler.writeListToJsonString(toDoList);
    }

    public String getToDoById(Long id) {
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
