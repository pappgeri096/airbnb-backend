package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.Pending;
import com.codecool.airbnbmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PendingRepository extends JpaRepository<Pending, Long> {

    Set<Pending> findAllByUser(User user);
    

}
