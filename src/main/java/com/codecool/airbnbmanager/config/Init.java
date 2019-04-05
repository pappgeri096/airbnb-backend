package com.codecool.airbnbmanager.config;

import com.codecool.airbnbmanager.model.*;
import com.codecool.airbnbmanager.repository.*;
import com.codecool.airbnbmanager.util.enums.LodgingsType;
import com.codecool.airbnbmanager.util.enums.RoleName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init implements CommandLineRunner {
    private PasswordEncoder encoder;

    private UserRepository userRepository;

    private LodgingsRepository lodgingsRepository;

    private RoleRepository roleRepository;

    private ToDoRepository toDoRepository;

    public Init(PasswordEncoder encoder, UserRepository userRepository, LodgingsRepository lodgingsRepository, RoleRepository roleRepository, ToDoRepository toDoRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.lodgingsRepository = lodgingsRepository;
        this.roleRepository = roleRepository;
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void run(String... args) {
        roleRepository.save(new Role(RoleName.ROLE_USER));
    }
}