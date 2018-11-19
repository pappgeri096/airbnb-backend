package com.codecool.airbnbmanager.repository;

import com.codecool.airbnbmanager.model.Lodgings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LodgingsRepository extends JpaRepository<Lodgings, Long> {
}
