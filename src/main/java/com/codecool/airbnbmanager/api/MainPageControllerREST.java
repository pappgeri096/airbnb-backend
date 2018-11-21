package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.service.UserServiceREST;
import com.codecool.airbnbmanager.util.enums.UserFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainPageControllerREST {

    private String userEmail = "guest@fakedomain.com";

    private final UserServiceREST userServiceREST;

    @Autowired
    public MainPageControllerREST(UserServiceREST userServiceREST) {
        this.userServiceREST = userServiceREST;
    }

    @GetMapping(path = {"/api", "/api/index"})
    public String indexView() {
        userEmail = "akincsei@gmail.com"; // todo: get email from session

        List<String> fieldsToInclude = new ArrayList<>();
        fieldsToInclude.add(UserFieldType.FIRST_NAME.getInputString());
        fieldsToInclude.add(UserFieldType.LANDLORD_LODGINGS.getInputString());

        return userServiceREST.createJsonStringByAndIncluding(userEmail, fieldsToInclude);
    }


}
