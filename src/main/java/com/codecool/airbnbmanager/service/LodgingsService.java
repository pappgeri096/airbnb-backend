package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.util.LodgingsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Lodgings> findAllLodgingsByUser(User user) {// todo: handles only landlords
        return lodgingsRepository.findAllByLandlord(user);
    }

    public Lodgings findLodgingsById(long id) {
        return lodgingsRepository.findLodgingsById(id);
    }

    public List<String> getEnumAsStringList() {
        return Arrays.stream(LodgingsType.values()).map(LodgingsType::getLodgingsTypeString).collect(Collectors.toList());
    }

    public void deleteLodgingsById(long id) {
        lodgingsRepository.deleteLodgingsById(id);
    }
}
