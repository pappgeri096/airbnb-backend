package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes
public class MainPageController {

    private List<Lodgings> lodgings;
    private User user;
    private String userEmail = "guest@fakedomain.com";

    private final UserService userService;
    private final LodgingsService lodgingsService;

    @ModelAttribute
    public void addAttributes(Model model) {
        userEmail = "akincsei@gmail.com"; // todo: get email from session
        user = userService.handleFindUserByEmail(userEmail);
        lodgings = lodgingsService.findAllLodgingsByUser(user);

        model.addAttribute("userData", user);
        model.addAttribute("lodgings", lodgings);

    }

    @Autowired
    public MainPageController(LodgingsService lodgingsService, UserService userService) {
        this.lodgingsService = lodgingsService;
        this.userService = userService;
    }


    @GetMapping(path = {"/", "/index"})
    public String indexView() {
        userEmail = "akincsei@gmail.com"; // todo: get email from session
        user = userService.handleFindUserByEmail(userEmail);
        lodgings = lodgingsService.findAllLodgingsByUser(user);

        return "index";
    }

}
