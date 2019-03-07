package com.codecool.airbnbmanager.api;

import com.codecool.airbnbmanager.message.request.UserInfo;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.UserRepository;
import com.codecool.airbnbmanager.util.enums.ToDoStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private String errorMessage = "User not found with this username!";

    @GetMapping(path = {"/{username}"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> userView(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(errorMessage));

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{username}/lodgings")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Lodgings>> getLodgingsByUserName(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(errorMessage));
        Set<Lodgings> lodgings = user.getTenantLodgings();

        return ResponseEntity.ok().body(lodgings);
    }

    @GetMapping("/{username}/landlord")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<Lodgings>> getLandordLodgings(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(errorMessage));
        Set<Lodgings> lodgings = user.getLandlordLodgings();

        return ResponseEntity.ok().body(lodgings);
    }

    @GetMapping("/{username}/todos")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Set<ToDo>> getTodosByUserName(@PathVariable("username") String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(errorMessage));
        Set<Lodgings> lodgings = user.getLandlordLodgings();

        Set<ToDo> todos = lodgings
                .stream()
                .flatMap(l -> l.getTodos().stream())
                .filter(toDo -> toDo.getStatus()== ToDoStatusType.NEW)
                .collect(Collectors.toSet());

        return ResponseEntity.ok().body(todos);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> editUser(@PathVariable("username") String username, @RequestBody UserInfo user) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(errorMessage));

        if(user.getPassword()!=null) currentUser.setPassword(encoder.encode(user.getPassword()));

        currentUser.setFirstName(user.getFirstname());
        currentUser.setSurname(user.getSurname());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

       userRepository.save(currentUser);

        return ResponseEntity.ok().body(Boolean.TRUE);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(errorMessage));

        userRepository.delete(user);

        return ResponseEntity.ok().body(Boolean.TRUE);
    }

}
