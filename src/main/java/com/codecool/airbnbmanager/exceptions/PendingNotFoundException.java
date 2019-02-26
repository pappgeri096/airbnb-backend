package com.codecool.airbnbmanager.exceptions;

public class PendingNotFoundException extends RuntimeException {
    public PendingNotFoundException(long id) {
        super("This Pending not found with this id " + id);
    }
}
