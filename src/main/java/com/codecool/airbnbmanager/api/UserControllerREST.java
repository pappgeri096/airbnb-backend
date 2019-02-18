package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsServiceREST;
import com.codecool.airbnbmanager.service.UserServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserControllerREST {

    @Autowired
    private UserServiceREST userServiceREST;

    @Autowired
    private LodgingsServiceREST lodgingsServiceREST;

    @GetMapping(path = {"/{username}"})
    public User userView(@PathVariable("username") String username) {
        return userServiceREST.getUserByUsername(username);
    }

    @GetMapping("/{username}/lodgings")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public Set<Lodgings> getLodgingsByUserName(@PathVariable("username") String username){
        return userServiceREST.getUserLodgings(username);
    }

    @GetMapping("/{username}/todos")
    @PreAuthorize("hasRole('USER')")
    public Set<ToDo> getTodosByUserName(@PathVariable("username") String username){
        return userServiceREST.getUserTodos(username);
    }

}
