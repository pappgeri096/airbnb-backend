package com.codecool.airbnbmanager.service;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
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

    @Autowired
    private UserRepository userRepository;

    public String createJsonString(long lodgingsId, List<String> fieldsToExclude) {
        Lodgings lodgings = lodgingsRepository.findLodgingsById(lodgingsId);

        String lodgingsDetails;

        try {
            lodgingsDetails = JsonMappingHandler.mapJavaObjectWithoutFields(lodgings, fieldsToExclude);
        } catch (JsonProcessingException e) {
            lodgingsDetails = "{\"data\":\"User not found\"}";
            e.printStackTrace();
        }

        return lodgingsDetails;
    }

    public boolean handleLodgingsUpdate(String lodgingsData){

        boolean isUpdateSuccesful = false;

        Map<String, String> lodgingsDataMap = new HashMap<>();

        lodgingsDataMap = JsonMappingHandler.convertJsonArraytoMap(lodgingsData);


        String lodgingsIdString = lodgingsDataMap.get(LodgingsFieldType.ID.getInputString());
        Lodgings lodgings = lodgingsRepository.findLodgingsById(Long.parseLong(lodgingsIdString));

        lodgings.setName(lodgingsDataMap.get(LodgingsFieldType.NAME.getInputString()));
        lodgings.setLodgingsType(LodgingsType.valueOf(lodgingsDataMap.get(LodgingsFieldType.LODGINGS_TYPE.getInputString())));
        lodgings.setPricePerDay(Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.PRICE_PER_DAY.getInputString())));
        lodgings.setElectricityBill(Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.ELECTRICITY_BILL.getInputString())));
        lodgings.setGasBill(Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.GAS_BILL.getInputString())));
        lodgings.setTelecommunicationBill(Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.TELECOMMUNICATION_BILL.getInputString())));
        lodgings.setCleaningCost(Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.CLEANING_COST.getInputString())));
        lodgings.setCountry(lodgingsDataMap.get(LodgingsFieldType.COUNTRY.getInputString()));
        lodgings.setCity(lodgingsDataMap.get(LodgingsFieldType.CITY.getInputString()));
        lodgings.setZipCode(lodgingsDataMap.get(LodgingsFieldType.ZIP_CODE.getInputString()));
        lodgings.setAddress(lodgingsDataMap.get(LodgingsFieldType.ADDRESS.getInputString()));

        lodgingsRepository.save(lodgings);

        isUpdateSuccesful = true;
        return isUpdateSuccesful;

    }

    public boolean handleLodgingsDeletion(long lodgingsId) {

        boolean isDeletionSuccessful = false;

        Lodgings lodgingsMayBeExists = lodgingsRepository.findLodgingsById(lodgingsId);
        if(lodgingsMayBeExists == null){
            return isDeletionSuccessful;
        }

        lodgingsRepository.deleteLodgingsById(lodgingsId);
        isDeletionSuccessful = true;

        return isDeletionSuccessful;
    }

    public boolean handleAddNewLodgings(String lodgingsData, String landlordEmail) {

        boolean isAddSuccesful = false;

        Map<String, String> lodgingsDataMap = new HashMap<>();

        lodgingsDataMap = JsonMappingHandler.convertJsonArraytoMap(lodgingsData);


        User landlordToAdd = userRepository.findUserByEmail(landlordEmail);

        String propertyManagerEmail = lodgingsDataMap.get(LodgingsFieldType.PROPERTY_MANAGER_LODGINGS.getInputString());
        User propertyManagerToAdd = userRepository.findUserByEmail(propertyManagerEmail);

        AddressBuilder fullAddress = new AddressBuilder(
                lodgingsDataMap.get(LodgingsFieldType.COUNTRY.getInputString()),
                lodgingsDataMap.get(LodgingsFieldType.CITY.getInputString()),
                lodgingsDataMap.get(LodgingsFieldType.ZIP_CODE.getInputString()),
                lodgingsDataMap.get(LodgingsFieldType.ADDRESS.getInputString())
        );

        Lodgings lodgingsToAdd = new Lodgings(
                lodgingsDataMap.get(LodgingsFieldType.NAME.getInputString()),
                LodgingsType.valueOf(lodgingsDataMap.get(LodgingsFieldType.LODGINGS_TYPE.getInputString())),
                Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.PRICE_PER_DAY.getInputString())),
                Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.ELECTRICITY_BILL.getInputString())),
                Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.GAS_BILL.getInputString())),
                Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.TELECOMMUNICATION_BILL.getInputString())),
                Long.parseLong(lodgingsDataMap.get(LodgingsFieldType.CLEANING_COST.getInputString())),
                landlordToAdd,
                propertyManagerToAdd,
                fullAddress
        );

        isAddSuccesful = true;
        return isAddSuccesful;
    }

    public String listAllLodgingsByUser(String userEmail) {
        User userToListLodgings = userRepository.findUserByEmail(userEmail);

        List<Lodgings> lodgingsByLandlord = lodgingsRepository.findAllByLandlord(userToListLodgings);
        List<Lodgings> lodgingsByPropertyManager = lodgingsRepository.findAllByPropertyManager(userToListLodgings);

        if(lodgingsByLandlord != null) {
            return JsonMappingHandler.mapJavaObjectListToJsonArray(lodgingsByLandlord);
        }

        return JsonMappingHandler.mapJavaObjectListToJsonArray(lodgingsByPropertyManager);
    }

    public void handleLodgingsAddition(Lodgings lodgings) {
        lodgingsRepository.save(lodgings);
    }

    Lodgings handleFindById(Long id) {
        return lodgingsRepository.findById(id).orElse(null);
    }

    public Lodgings handleAddTodoToLodgings(Long id, ToDo newToDo) {

        Lodgings lodgings = lodgingsRepository.findById(id).orElse(null);

        if (lodgings != null) {
            lodgings.addTodo(newToDo);
        }

        lodgingsRepository.save(lodgings);

        return lodgingsRepository.findById(id).orElse(null);

    }
}
