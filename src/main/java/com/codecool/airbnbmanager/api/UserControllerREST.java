package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.message.response.ResponseMessage;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.UserServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @PutMapping("/{username}/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editUser(@PathVariable("username") String username, User user) {
        if(!userServiceREST.updateUser(user, username)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseMessage("User updated successfully!"), HttpStatus.OK);
    }

}
