package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.UserService;
import com.codecool.airbnbmanager.util.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// todo: session!!!!!!!!!!
@Controller
public class UserController {

    private String firstName;
    private User user;

    private final UserService userService;


    @ModelAttribute
    public void addAttributes(Model model) {
        String userEmail = "akincsei@gmail.com";
        User user = userService.handleFindUserByEmail(userEmail);
        firstName = user.getFirstName();

        model.addAttribute("firstName", firstName);
    }


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public String userAdd(Model model) {
        return null;
    }

    @GetMapping(path = "/user")
    public String userView(Model model) {
        String userEmail = "akincsei@gmail.com";
        user = userService.handleFindUserByEmail(userEmail);
        firstName = user.getFirstName();

        model.addAttribute("firstName", firstName);
        model.addAttribute("userData", user);

        return "view_user";
    }

    @GetMapping(path = "/user/edit")
    public String getUserEdit(Model model) {
        String userEmail = "akincsei@gmail.com";
        user = userService.handleFindUserByEmail(userEmail);
        firstName = user.getFirstName();

        model.addAttribute("firstName", firstName);
        model.addAttribute("userData", user);

        return "edit_user";
    }

    @PostMapping(path = "/user/edit")
    public String postEditedUserData(
            @RequestParam(name = "first_name")String firstName,
            @RequestParam(name = "surname")String surName,
            @RequestParam(name = "old_password")String oldPassword,
            @RequestParam(name = "new_password")String newPassword,
            @RequestParam(name = "phone_number")String phoneNumber,
            @RequestParam(name = "country")String country,
            @RequestParam(name = "city")String city,
            @RequestParam(name = "zip_code")String zipCode,
            @RequestParam(name = "address")String address
    )
    {

        String passwordHash = user.getPasswordHash();
        boolean isPasswordCorrect = PasswordHashing.checkPassword(oldPassword, passwordHash);

        if (isPasswordCorrect) {
            user.setFirstName(firstName);
            user.setSurname(surName);
            user.setPhoneNumber(phoneNumber);
            user.setCountry(country);
            user.setCity(city);
            user.setZipCode(zipCode);
            user.setAddress(address);

            boolean isPasswordUpdated = !newPassword.equals("");

            if (isPasswordUpdated) {
                user.setPasswordHash(PasswordHashing.hashPassword(newPassword));
            }

            userService.update(user);
            return "redirect:/user";

        } else {
            // todo: send http error code to site
            return "redirect:/user/edit";
        }



    }


    @PostMapping(path = "/user/delete")
    public String userDelete(Model model) {
        return null;
    }

}
