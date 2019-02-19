package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.repository.LodgingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
