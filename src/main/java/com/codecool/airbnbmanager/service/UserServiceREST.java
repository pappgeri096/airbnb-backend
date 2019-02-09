package com.codecool.airbnbmanager.service;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceREST {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);

    @Autowired
    private UserRepository userRepository;

    private User handleFindUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail);
    }


    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void add(User user) {
        userRepository.save(user);
    }
}
