package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    User findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);
}
