package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes
public class IndexController {

    private String firstName;

    private final LodgingsService lodgingsService;

    private final UserService userService;

    @Autowired
    public IndexController(LodgingsService lodgingsService, UserService userService) {
        this.lodgingsService = lodgingsService;
        this.userService = userService;
    }


    @GetMapping(path = {"/", "/index"})
    public String indexView(Model model) {
        String userEmail = "akincsei@gmail.com";
        User user = userService.handleFindUserByEmail(userEmail);
        firstName = user.getFirstName();
        List<Lodgings> lodgings = lodgingsService.findAllLodgingsByUser(user);

        model.addAttribute("firstName", firstName);
        model.addAttribute("lodgings", lodgings);

        return "index";
    }

}
