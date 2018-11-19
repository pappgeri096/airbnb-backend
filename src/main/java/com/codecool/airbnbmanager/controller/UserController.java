package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    private final LodgingsService lodgingsService;

    @Autowired
    public UserController(UserService userService, LodgingsService lodgingsService) {
        this.userService = userService;
        this.lodgingsService = lodgingsService;
    }
}
