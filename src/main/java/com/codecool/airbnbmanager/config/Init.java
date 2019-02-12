package com.codecool.airbnbmanager.config;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.Role;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.Address;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.RoleRepository;
import com.codecool.airbnbmanager.repository.UserRepository;
import com.codecool.airbnbmanager.util.enums.LodgingsType;
import com.codecool.airbnbmanager.util.enums.RoleName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Init implements CommandLineRunner {
    private PasswordEncoder encoder;

    private UserRepository userRepository;

    private LodgingsRepository lodgingsRepository;
    private RoleRepository roleRepository;

    public Init(PasswordEncoder encoder, UserRepository userRepository, LodgingsRepository lodgingsRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.lodgingsRepository = lodgingsRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("Hungary", "Budapest", "4444", "Paroka street");
        Address address2 = new Address("Hungary", "Budapest", "4444", "Paroka street");
        Address address3 = new Address("Hungary", "Budapest", "4444", "Paroka street");
        User user = new User("pokroc", "Nagyika", "Paplan", "paplan@gmail.com", "+36306185528",
                address,
                encoder.encode("12345678"));

        User user2 = new User("pokrocka", "Nagyika", "Paplan", "paplan@gmail.com", "+36306185528",
                address3,
                encoder.encode("12345678"));
        Set<Role> roles = new HashSet<>();

        roleRepository.save(new Role(RoleName.ROLE_USER));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);

        user2.setRoles(roles);
        user.setRoles(roles);
        userRepository.save(user);
        userRepository.save(user2);

        Lodgings lodgings = new Lodgings("Paroka Hotel", LodgingsType.APARTMENT, 1000, 444, 555, 666, 6666, user2,
                address2);
        lodgings.setTenants(user);
        lodgings.setPropertyManager(user2);
        lodgingsRepository.save(lodgings);
    }
}
