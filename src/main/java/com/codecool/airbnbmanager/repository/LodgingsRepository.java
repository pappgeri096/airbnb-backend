package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LodgingsRepository extends JpaRepository<Lodgings, Long> {

    List<Lodgings> findAllByLandlord(User landlord);
    Lodgings findLodgingsById(long id);
    void deleteLodgingsById(long id);

}
