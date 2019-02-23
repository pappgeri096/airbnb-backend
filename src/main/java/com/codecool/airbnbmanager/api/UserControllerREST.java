package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.message.request.UserInfo;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserControllerREST {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = {"/{username}"})
    public User userView(@PathVariable("username") String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @GetMapping("/{username}/lodgings")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public Set<Lodgings> getLodgingsByUserName(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username).orElse(null);
        Set<Lodgings> lodgings = user!=null ? user.getTenantLodgings() : null;
        if (lodgings==null) return null;
        return lodgings;
    }

    @GetMapping("/{username}/todos")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public Set<ToDo> getTodosByUserName(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username).orElse(null);
        Set<Lodgings> lodgings = user!=null ? user.getTenantLodgings() : null;
        if (lodgings==null) return null;
        return lodgings
                .stream()
                .flatMap(l -> l.getTodos().stream())
                .collect(Collectors.toSet());
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public User editUser(@PathVariable("username") String username, @RequestBody UserInfo user) {
        User currentUser = userRepository.findByUsername(username).orElse(null);
        if(currentUser==null) return null;

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

        return userRepository.save(currentUser);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('USER') OR hasRole('LANDLORD')")
    public void deleteUser(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user==null) return;
        userRepository.delete(user);
    }

}
