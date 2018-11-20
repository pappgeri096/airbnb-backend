package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainPageControllerREST {

    private List<Lodgings> lodgings;
    private User user;
    private String userEmail = "guest@fakedomain.com";

    private final UserService userService;
    private final LodgingsService lodgingsService;

    @Autowired
    public MainPageControllerREST(LodgingsService lodgingsService, UserService userService) {
        this.lodgingsService = lodgingsService;
        this.userService = userService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        userEmail = "akincsei@gmail.com"; // todo: get email from session
        user = userService.handleFindUserByEmail(userEmail);
        lodgings = lodgingsService.findAllLodgingsByUser(user);

        model.addAttribute("userData", user);
        model.addAttribute("lodgings", lodgings);

    }


}
