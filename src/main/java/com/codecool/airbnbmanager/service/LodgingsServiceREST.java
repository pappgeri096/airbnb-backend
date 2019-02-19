package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.Address;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import com.codecool.airbnbmanager.repository.UserRepository;
import com.codecool.airbnbmanager.util.JsonMappingHandler;
import com.codecool.airbnbmanager.util.LodgingsFieldType;
import com.codecool.airbnbmanager.util.enums.LodgingsType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LodgingsServiceREST {

    @Autowired
    private LodgingsRepository lodgingsRepository;

    public Lodgings getLodgingsById(long id) {
        return lodgingsRepository.findById(id).get();
    }

    public void addNewLodgings(Lodgings lodgings) {
        lodgingsRepository.save(lodgings);
    }

    public boolean updateLodgings(Lodgings updatedLodgings, long lodgingsId) {


        Lodgings currentLodgings = getLodgingsById(lodgingsId);

        if(currentLodgings==null) return false;

        currentLodgings.setName(updatedLodgings.getName());
        currentLodgings.setLodgingsType(updatedLodgings.getLodgingsType());
        currentLodgings.setPricePerDay(updatedLodgings.getPricePerDay());
        currentLodgings.setElectricityBill(updatedLodgings.getElectricityBill());
        currentLodgings.setGasBill(updatedLodgings.getGasBill());
        currentLodgings.setTelecommunicationBill(updatedLodgings.getTelecommunicationBill());
        currentLodgings.setCleaningCost(updatedLodgings.getCleaningCost());
        currentLodgings.setFullAddress(updatedLodgings.getFullAddress());

        lodgingsRepository.save(currentLodgings);

        return true;
    }

    public void deleteLodgings(long id) {
        lodgingsRepository.deleteById(id);
    }
}
