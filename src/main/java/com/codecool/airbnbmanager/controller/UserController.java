package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LodgingsService lodgingsService;

}
