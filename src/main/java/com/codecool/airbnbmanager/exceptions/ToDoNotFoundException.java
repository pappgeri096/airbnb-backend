package com.codecool.airbnbmanager.exceptions;

public class ToDoNotFoundException extends RuntimeException {
    public ToDoNotFoundException(long id) {
        super("This ToDo not found with this id: " + id);
    }
}
