package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LodgingsService {

    private final LodgingsRepository lodgingsRepository;

    @Autowired
    public LodgingsService(LodgingsRepository lodgingsRepository) {
        this.lodgingsRepository = lodgingsRepository;
    }

    public void add(Lodgings lodgings) {
        lodgingsRepository.save(lodgings);
    }
}
