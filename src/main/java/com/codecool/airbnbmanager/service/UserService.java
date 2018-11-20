package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public User handleFindUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail);
    }

    public boolean handleUserDeletionBy(String userId, User user) {
        if (user.getId() == Long.parseLong(userId)) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }

    }
}
