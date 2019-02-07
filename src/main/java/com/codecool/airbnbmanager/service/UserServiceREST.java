package com.codecool.airbnbmanager.service;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.repository.UserRepository;
import com.codecool.airbnbmanager.util.*;
import com.codecool.airbnbmanager.util.enums.UserFieldType;
import com.codecool.airbnbmanager.util.enums.UserType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

}
