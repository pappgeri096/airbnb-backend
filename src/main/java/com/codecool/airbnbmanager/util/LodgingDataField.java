package com.codecool.airbnbmanager.util;

public enum LodgingDataField {
    ID("id"),
    NAME("lodging_name"),
    TYPE("lodging_type"),
    DAILY_PRICE("daily_price"),
    ELECTRICITY_BILL("electricity_bill"),
    GAS_BILL("gas_bill"),
    TELECOMMUNICATION_BILL("telecommunication_bill"),
    CLEANING_COST("cleaning_cost");

    private final String inputString;

    LodgingDataField(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
