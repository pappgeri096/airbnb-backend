package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

}
