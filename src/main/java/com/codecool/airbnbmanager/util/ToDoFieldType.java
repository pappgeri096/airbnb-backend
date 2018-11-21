package com.codecool.airbnbmanager.util;

public enum ToDoFieldType {
    ID("id"),
    NAME("name"),
    LODGINGS("lodgings"),
    LODGINGS_ID("lodgingsId"),
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
