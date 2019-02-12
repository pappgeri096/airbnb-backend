package com.codecool.airbnbmanager.service;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceREST {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);

    @Autowired
    private UserRepository userRepository;

    private LodgingsServiceREST lodgingsServiceREST;

    private User handleFindUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail);
    }


    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public Set<Lodgings> getUserLodgings(String username) {

        return userRepository.findUserByUsername(username).getTenantLodgings();
    }

    public Set<ToDo> getUserTodos(String username) {
        Set<Lodgings> lodgings = getUserLodgings(username);
        Set<ToDo> toDos = new HashSet<>();

        for(Lodgings l : lodgings){
            toDos.addAll(l.getTodos());
        }
        return toDos;
    }
}
