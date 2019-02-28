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
    public void run(String... args) throws Exception {
        Address address = new Address("Hungary", "Budapest", "4444", "Paroka street");
        Address address2 = new Address("Hungary", "Budapest", "4444", "Paroka street");
        Address address3 = new Address("Hungary", "Budapest", "4444", "Paroka street");
        Address address4 = new Address("Hungary", "Budapest", "4444", "Paroka street");
        User user = new User("pokroc", "Nagyika", "Paplan", "paplan@gmail.com", "+36306185528",
                address,
                encoder.encode("12345678"));

        User user2 = new User("pokrocka", "Nagyika", "Paplan", "diszno@gmail.com", "+36306185528",
                address3,
                encoder.encode("12345678"));
        Set<Role> roles = new HashSet<>();
        Set<Role> roles2 = new HashSet<>();

        roleRepository.save(new Role(RoleName.ROLE_USER));
        roleRepository.save(new Role(RoleName.ROLE_PROPERTY));
        roleRepository.save(new Role(RoleName.ROLE_LANDLORD));

        Role userRole = roleRepository.findByName(RoleName.ROLE_LANDLORD)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

        Role landlordRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(landlordRole);
        roles2.add(userRole);

        user2.setRoles(roles);
        user.setRoles(roles2);
        userRepository.save(user);
        userRepository.save(user2);

        Lodgings lodgings = new Lodgings("Paroka Hotel", LodgingsType.FAMILY_HOUSE, 1000, 444, 555, 666, 6666, user,
                address2);
        lodgings.setTenants(user);
        lodgings.setPropertyManager(user2);
        lodgingsRepository.save(lodgings);

        Lodgings lodgings2 = new Lodgings("Diszno Street", LodgingsType.FAMILY_HOUSE, 1000, 444, 555, 666, 6666, user,
                address4);

        lodgingsRepository.save(lodgings2);

        ToDo toDo = new ToDo("ELtort az ablak", lodgings, new Date(),"Nem kene", 1000);
        toDoRepository.save(toDo);

    }
}
