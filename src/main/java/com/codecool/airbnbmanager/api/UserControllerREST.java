package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.api.UserServiceREST;
import com.codecool.airbnbmanager.util.UserFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(path = "/api/user")
    public String userView() {
        return userServiceREST.createJsonStringByAndExcluding(userEmail, fieldsToExcludeFromViewEdit);
    }

    @GetMapping(path = "/api/user/edit")
    public String getUserEdit() {
        return userServiceREST.createJsonStringByAndExcluding(userEmail, fieldsToExcludeFromViewEdit);
    }

    @PutMapping(path = "/api/user/edit", consumes = "text/plain")
    public String postEditedUserData(@RequestBody String body) {
        boolean isUpdateSuccessFul = userServiceREST.handleUserUpdate(body);
        return (isUpdateSuccessFul) ? "SUCCESS" : "FAIL";
    }


    @DeleteMapping(path = "/api/user/delete", consumes = "text/plain")
    public String userDeletion(@RequestBody String body) {
        boolean isdDeletionSuccessful = userServiceREST.handleUserDeletionBy(body);
        return (isdDeletionSuccessful) ? "SUCCESS" : "FAIL";
    }


//    @PostMapping(path = "/api/login", consumes = "text/plain")
//    public String loginPost(@RequestBody String body) {
//        // todo: in SB security
//        return "redirect:index";
//    }

    @PostMapping(path = "/api/user/add")
    public String userAddPost(@RequestBody String body) {

        boolean isAdditionSuccessFul = userServiceREST.handleUserAddition(body);
        return (isAdditionSuccessFul) ? "SUCCESS" : "FAIL";
    }
}
