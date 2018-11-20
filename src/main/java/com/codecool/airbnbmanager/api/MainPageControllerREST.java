package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import com.codecool.airbnbmanager.service.api.MainPageServiceREST;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainPageControllerREST {

    private String userEmail = "guest@fakedomain.com";

    private final UserService userService;
    private final LodgingsService lodgingsService;
    private final MainPageServiceREST mainPageServiceREST;

    @Autowired
    public MainPageControllerREST(UserService userService, LodgingsService lodgingsService, MainPageServiceREST mainPageServiceREST) {
        this.userService = userService;
        this.lodgingsService = lodgingsService;
        this.mainPageServiceREST = mainPageServiceREST;
    }

    @GetMapping(path = {"/api", "/api/index"})
    public String indexView() {
        userEmail = "akincsei@gmail.com"; // todo: get email from session
//        JSONArray lodgingsWithUserEmail = mainPageServiceREST.handleLodgingsWithUserEmail(userEmail);

        return mainPageServiceREST.handleLodgingsWithUserEmailJackson(userEmail); // lodgingsWithUserEmail.toString(2);
    }


}
