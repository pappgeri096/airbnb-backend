package com.codecool.airbnbmanager.model.builder;

import com.codecool.airbnbmanager.model.Address;
import com.codecool.airbnbmanager.model.User;

public class UserBuilder {
    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public User createUser() {
        return new User(username, firstName, surname, email, phoneNumber, password);
    }
}