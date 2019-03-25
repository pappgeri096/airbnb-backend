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
        Address address2 = new Address("Hungary", "Budapest", "1065", "Nagymező u. 44");
        Address address4 = new Address("Hungary", "Miskolc", "3525", "Régiposta u. 9.");
        User user = new User("kedvesMiki", "What", "Who!", "kedvesmiki@gmail.com", "+36306000000",
                encoder.encode("12345678"));

        User user2 = new User("vidisBali", "Farago", "Balazs", "vidisbali@gmail.com", "+36306000001",
                encoder.encode("12345678"));
        Set<Role> roles = new HashSet<>();
        Set<Role> roles2 = new HashSet<>();

        roleRepository.save(new Role(RoleName.ROLE_USER));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

        Role landlordRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(landlordRole);
        roles2.add(userRole);

        user2.setRoles(roles);
        user.setRoles(roles2);
        userRepository.save(user);
        userRepository.save(user2);

        Lodgings lodgings = new Lodgings("CodeCool Budapest", LodgingsType.FAMILY_HOUSE, 250000, 30000, 10000, 10000, 25000, user,
                address2);
        lodgings.setTenants(user);
        lodgingsRepository.save(lodgings);

        Lodgings lodgings2 = new Lodgings("CodeCool Miskolc", LodgingsType.FAMILY_HOUSE, 1000, 444, 555, 666, 6666, user,
                address4);
        lodgings2.setTenants(user2);
        lodgingsRepository.save(lodgings2);

        ToDo toDo = new ToDo("ELtort az ablak", lodgings, new Date(),"Az egyik idi.. diak 'veletlenul' eltorte. ", 7000);
        ToDo toDo2 = new ToDo("Nem mukodik a lift", lodgings, new Date(),"Nem mukodik.", 10000);
        ToDo toDo3 = new ToDo("Nem lehet lehuzni a wct", lodgings, new Date(),"Szag...", 10000);
        toDoRepository.save(toDo);
        toDoRepository.save(toDo2);
        toDoRepository.save(toDo3);

    }
}
