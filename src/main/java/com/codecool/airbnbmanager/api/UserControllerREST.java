package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.UserServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserControllerREST {

    @Autowired
    private UserServiceREST userServiceREST;

    @GetMapping(path = {"/api/user", "/api/user/edit"})
    public User userView() {
        return new User();
    }

    @PutMapping(path = "/api/user/edit", consumes = "text/plain")
    public boolean postEditedUserData(@RequestBody String body) {
        return userServiceREST.handleUserUpdate(body);
    }


    @DeleteMapping(path = "/api/user/delete/{id}")
    public boolean userDeletion(@PathVariable(name = "id") Long id) {
        return userServiceREST.handleUserDeletionBy(id);
    }

    @PostMapping(path = "/api/user/add")
    public boolean userAddPost(@RequestBody String body) {
        return userServiceREST.handleUserAddition(body);
    }
}
