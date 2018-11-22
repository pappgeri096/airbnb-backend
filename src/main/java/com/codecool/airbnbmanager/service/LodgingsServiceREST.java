package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LodgingsServiceREST {

    @Autowired
    private LodgingsRepository lodgingsRepository;

    public void handleLodgingsAddition(Lodgings lodgings) {
        lodgingsRepository.save(lodgings);
    }

    Lodgings handleFindById(Long id) {
        return lodgingsRepository.findById(id).orElse(null);
    }

}
