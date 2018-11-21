package com.codecool.airbnbmanager.util;

public enum ToDoFieldType {
    ID("id"),
    FIRST_NAME("first_name"),
    NAME("name"),
    LODGINGS("lodgings"),
    DEADLINE("deadline"),
    DESCRIPTION("description"),
    PRICE("price"),
    STATUS("status"),
    OBSOLETE("obsolete");

    private final String inputString;

    ToDoFieldType(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
