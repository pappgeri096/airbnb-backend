package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.service.UserServiceREST;
import com.codecool.airbnbmanager.util.enums.UserFieldType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.airbnbmanager.configuration.Initializer.FAIL_MESSAGE;
import static com.codecool.airbnbmanager.configuration.Initializer.SUCCESS_MESSAGE;

// todo: session!!!!!!!!!!
@RestController
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserControllerREST {

    private String userEmail = "akincsei@gmail.com"; // todo: get it from session
    private List<String> fieldsToExcludeFromViewEdit = new ArrayList<>();

    {
        fieldsToExcludeFromViewEdit.add(UserFieldType.LANDLORD_LODGINGS.getInputString());
        fieldsToExcludeFromViewEdit.add(UserFieldType.PROPERTY_MANAGER_LODGINGS.getInputString());
        fieldsToExcludeFromViewEdit.add(UserFieldType.PASSWORD_HASH.getInputString());
    }

    @Autowired
    private UserServiceREST userServiceREST;

    @GetMapping(path = {"/api/user", "/api/user/edit"})
    public String userView() {
        return userServiceREST.createJsonStringByAndExcluding(userEmail, fieldsToExcludeFromViewEdit);
    }

    @PutMapping(path = "/api/user/edit", consumes = "text/plain")
    public String postEditedUserData(@RequestBody String body) {
        boolean isUpdateSuccessFul = userServiceREST.handleUserUpdate(body);
        return (isUpdateSuccessFul) ? SUCCESS_MESSAGE : FAIL_MESSAGE;
    }


    @DeleteMapping(path = "/api/user/delete/{id}")
    public String userDeletion(@PathVariable(name = "id") Long id) {
        boolean isdDeletionSuccessful = userServiceREST.handleUserDeletionBy(id);
        return (isdDeletionSuccessful) ? SUCCESS_MESSAGE : FAIL_MESSAGE;
    }

    @PostMapping(path = "/api/user/add")
    public String userAddPost(@RequestBody String body) {

        boolean isAdditionSuccessFul = userServiceREST.handleUserAddition(body);
        return (isAdditionSuccessFul) ? SUCCESS_MESSAGE : FAIL_MESSAGE;
    }
}
