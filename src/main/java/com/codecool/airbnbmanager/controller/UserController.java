package com.codecool.airbnbmanager.controller;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.service.UserService;
import com.codecool.airbnbmanager.util.PasswordHashing;
import com.codecool.airbnbmanager.util.UserFactory;
import com.codecool.airbnbmanager.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// todo: session!!!!!!!!!!
@Controller
@ControllerAdvice
public class UserController {

    private String errorMessage = "You are not logged in. Log in or register, please";
    private User user;
    private String userEmail = "akincsei@gmail.com";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public void addAttributes(Model model) {
        user = userService.handleFindUserByEmail(userEmail);
        model.addAttribute("userData", user);
        model.addAttribute("errorMessage", errorMessage);
    }


    @GetMapping(path = "/user")
    public String userView() {
        // todo get it from session
        userEmail = "akincsei@gmail.com";
        user = userService.handleFindUserByEmail(userEmail);
        return "view_user";
    }

    @GetMapping(path = "/user/edit")
    public String getUserEdit() {
        String userEmail = "akincsei@gmail.com";
        user = userService.handleFindUserByEmail(userEmail);
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
    public String userDeletion(@RequestParam(name = "user_id")String userId) {
        boolean idDeletionSuccessful = userService.handleUserDeletionBy(userId, user);
        if (idDeletionSuccessful) {
            userEmail = "guest@fakedomain.com";
            return "redirect:/login";
        } else {
            return "You are not allowed to delete this user"; // todo: send error page with error code and message
        }

    }

    @GetMapping(path = "/login")
    public String loginGet() {
        errorMessage = "You are logged out";

        return "login";
    }

    @PostMapping(path = "/login")
    public String loginPost() {

        return "redirect:index";
    }


    @GetMapping(path = "/registration")
    public String userAddGet() {
        return "registration";
    }

    @PostMapping(path = "/user/add")
    public String userAddPost(
            @RequestParam(name = "first_name")String firstName,
            @RequestParam(name = "surname")String surName,
            @RequestParam(name = "email")String email,
            @RequestParam(name = "user_type")String userType,
            @RequestParam(name = "password")String password,
            @RequestParam(name = "password_confirmation")String passwordConfirmation,
            @RequestParam(name = "phone_number")String phoneNumber,
            @RequestParam(name = "country")String country,
            @RequestParam(name = "city")String city,
            @RequestParam(name = "zip_code")String zipCode,
            @RequestParam(name = "address")String address
    ) {
        if (password.equals(passwordConfirmation)) {
            AddressBuilder fullAddress = new AddressBuilder(country, city, zipCode, address);
            User newUser = UserFactory.createUserInstanceBy(
                    UserType.valueOf(userType.toUpperCase()),
                    firstName,
                    surName,
                    email,
                    phoneNumber,
                    PasswordHashing.hashPassword(password),
                    fullAddress
            );
            userService.add(newUser);
            return "redirect:login";

        } else {
            errorMessage = "Password and confirmation do not match";
            return "redirect:registration";
        }
    }

    @GetMapping(path = "/logout")
    public String logout() {
        return "redirect:login";
    }

}
