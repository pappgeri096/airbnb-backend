package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.api.UserServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;

// todo: session!!!!!!!!!!
@RestController
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserControllerREST {

    private String errorMessage = "You are not logged in. Log in or register, please";
    private User user;
    private String userEmail = "akincsei@gmail.com";

    @Autowired
    private UserServiceREST userServiceREST;


    @GetMapping(path = "/api/user")
    public String userView() {
        // todo get it from session
        userEmail = "akincsei@gmail.com";
        return userServiceREST.handleRequestForJsonStringFromUserBy(userEmail);
    }

//    @GetMapping(path = "/api/user/edit")
//    public String getUserEdit() {
//        String userEmail = "akincsei@gmail.com";
//        user = userServiceREST.handleFindUserByEmail(userEmail);
//        return "edit_user";
//    }
//
//    // todo rewrite
//    @RequestMapping(
//            value = "/process",
//            method = RequestMethod.POST,
//            consumes = "text/plain")
//    public void process(@RequestBody String payload) throws Exception {
//
//        System.out.println(payload);
//
//    }
//
//    @PostMapping(path = "/api/user/edit")
//    public String postEditedUserData(
//            @RequestParam(name = "first_name")String firstName,
//            @RequestParam(name = "surname")String surName,
//            @RequestParam(name = "old_password")String oldPassword,
//            @RequestParam(name = "new_password")String newPassword,
//            @RequestParam(name = "phone_number")String phoneNumber,
//            @RequestParam(name = "country")String country,
//            @RequestParam(name = "city")String city,
//            @RequestParam(name = "zip_code")String zipCode,
//            @RequestParam(name = "address")String address
//    )
//    {
//
//        String passwordHash = user.getPasswordHash();
//        boolean isPasswordCorrect = PasswordHashing.checkPassword(oldPassword, passwordHash);
//
//        if (isPasswordCorrect) {
//            user.setFirstName(firstName);
//            user.setSurname(surName);
//            user.setPhoneNumber(phoneNumber);
//            user.setCountry(country);
//            user.setCity(city);
//            user.setZipCode(zipCode);
//            user.setAddress(address);
//
//            boolean isPasswordUpdated = !newPassword.equals("");
//
//            if (isPasswordUpdated) {
//                user.setPasswordHash(PasswordHashing.hashPassword(newPassword));
//            }
//
//            userService.update(user);
//            return "redirect:/user";
//
//        } else {
//            // todo: send http error code to site
//            return "redirect:/user/edit";
//        }
//    }
//
//
//    @PostMapping(path = "/api/user/delete")
//    public String userDeletion(@RequestParam(name = "user_id")String userId) {
//        boolean idDeletionSuccessful = userService.handleUserDeletionBy(userId, user);
//        if (idDeletionSuccessful) {
//            userEmail = "guest@fakedomain.com";
//            return "redirect:/login";
//        } else {
//            return "You are not allowed to delete this user"; // todo: send error page with error code and message
//        }
//
//    }
//
//    @GetMapping(path = "/api/login")
//    public String loginGet() {
//        errorMessage = "You are logged out";
//
//        return "login";
//    }
//
//    @PostMapping(path = "/api/login")
//    public String loginPost() {
//
//        return "redirect:index";
//    }
//
//
//    @GetMapping(path = "/api/registration")
//    public String userAddGet() {
//        return "registration";
//    }
//
//    @PostMapping(path = "/api/user/add")
//    public String userAddPost(
//            @RequestParam(name = "first_name")String firstName,
//            @RequestParam(name = "surname")String surName,
//            @RequestParam(name = "email")String email,
//            @RequestParam(name = "user_type")String userType,
//            @RequestParam(name = "password")String password,
//            @RequestParam(name = "password_confirmation")String passwordConfirmation,
//            @RequestParam(name = "phone_number")String phoneNumber,
//            @RequestParam(name = "country")String country,
//            @RequestParam(name = "city")String city,
//            @RequestParam(name = "zip_code")String zipCode,
//            @RequestParam(name = "address")String address
//    ) {
//        if (password.equals(passwordConfirmation)) {
//            AddressBuilder fullAddress = new AddressBuilder(country, city, zipCode, address);
//            User newUser = UserFactory.createUserInstanceBy(
//                    UserType.valueOf(userType.toUpperCase()),
//                    firstName,
//                    surName,
//                    email,
//                    phoneNumber,
//                    PasswordHashing.hashPassword(password),
//                    fullAddress
//            );
//            userService.add(newUser);
//            return "redirect:login";
//
//        } else {
//            errorMessage = "Password and confirmation do not match";
//            return "redirect:registration";
//        }
//    }
//
//    @GetMapping(path = "/api/logout")
//    public String logout() {
//        return "redirect:login";
//    }

}
