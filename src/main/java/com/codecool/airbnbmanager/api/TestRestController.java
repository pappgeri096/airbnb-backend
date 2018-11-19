package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    private final UserService userService;

    private final LodgingsService lodgingsService;

    @Autowired
    public TestRestController(UserService userService, LodgingsService lodgingsService) {
        this.userService = userService;
        this.lodgingsService = lodgingsService;
    }
}
