package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.UserServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserControllerREST {

    @Autowired
    private UserServiceREST userServiceREST;

    @GetMapping(path = {"/{username}"})
    public User userView(@PathVariable("username") String username) {
        return userServiceREST.getUserByUsername(username);
    }

}
