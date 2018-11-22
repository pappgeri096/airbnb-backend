package com.codecool.airbnbmanager.util;

public enum LodgingsFieldType {
    ID("id"),
    NAME("name"),
    LODGINGS_TYPE("lodgingsType"),
    PRICE_PER_DAY("pricePerDay"),
    ELECTRICITY_BILL("electricityBill"),
    GAS_BILL("gasBill"),
    TELECOMMUNICATION_BILL("telecommunicationBill"),
    CLEANING_COST("cleaningCost"),
    COUNTRY("country"),
    CITY("city"),
    ZIP_CODE("zipCode"),
    ADDRESS("address"),
    FULL_ADDRESS("fullAddress"),
    PROPERTY_MANAGER_LODGINGS("propertyManagerLodgings"),
    LANDLORD_LODGINGS("landlordLodgings");

    private final String inputString;

    LodgingsFieldType(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
