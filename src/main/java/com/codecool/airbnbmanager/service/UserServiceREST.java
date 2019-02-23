package com.codecool.airbnbmanager.service;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.message.request.UserInfo;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceREST {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder encoder;

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

    public boolean updateUser(UserInfo user, String username) {
        User currentUser = userRepository.findUserByUsername(username);
        if(currentUser==null) return false;

        if(user.getPassword()!=null) currentUser.setPassword(encoder.encode(user.getPassword()));

        currentUser.setFirstName(user.getFirstname());
        currentUser.setSurname(user.getSurname());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        currentUser.getFullAddress()
                .setCountry(user.getAddress().getCountry());

        currentUser.getFullAddress()
                .setCity(user.getAddress().getCity());

        currentUser.getFullAddress()
                .setZipCode(user.getAddress().getZipCode());

        currentUser.getFullAddress()
                .setAddress(user.getAddress().getAddress());

        userRepository.save(currentUser);
        return true;
    }

    public boolean deleteUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if(user==null) return false;

        userRepository.delete(user);
        return true;
    }
}
