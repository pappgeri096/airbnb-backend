package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.UserRepository;
import com.codecool.airbnbmanager.util.PasswordHashing;
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

    public boolean isPasswordCorrect(String candidate, String email) {
        User user = userRepository.findUserByEmail(email);
        String hashed = user.getPasswordHash();

        return PasswordHashing.checkPassword(candidate, hashed);
    }


}
