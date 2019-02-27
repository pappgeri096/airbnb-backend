package com.codecool.airbnbmanager.exceptions;

public class LodgingsNotFoundException extends RuntimeException{

    public LodgingsNotFoundException(long id) {
        super("Lodgings not found by this id:" + id);
    }
}
