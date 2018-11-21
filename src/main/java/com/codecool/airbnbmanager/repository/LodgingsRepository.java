package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LodgingsRepository extends JpaRepository<Lodgings, Long> {

    List<Lodgings> findAllByLandlord(User landlord);

}
