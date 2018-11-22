package com.codecool.airbnbmanager.service.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;

import java.util.List;

public class ToDoService {

//    private final ToDoDao toDoDao;
//    private final BaseService<User> userHandler;
//
//    public ToDoService(ToDoDao toDoDao, BaseService<User> userHandler) {
//        this.toDoDao = toDoDao;
//        this.userHandler = userHandler;
//    }
//
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
