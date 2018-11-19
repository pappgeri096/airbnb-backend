package com.codecool.airbnbmanager.util;

public enum FieldType {
    USER_OBJECT("user_object"),
    ID("id"),
    FIRST_NAME("first_name"),
    SURNAME("surname"),
    FULL_NAME("full_name"),
    USER_TYPE("user_type"),
    EMAIL_ADDRESS("email"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("password_confirmation"),
    PASSWORD_HASH("password_hash"),
    PHONE_NUMBER("phone_number"),
    COUNTRY("country"),
    CITY("city"),
    ZIP_CODE("zip_code"),
    ADDRESS("address"),
    PROPERTY_MANAGER_LODGINGS("propertyManagerLodgings"),
    LANDLORD_LODGINGS("landlordLodgings");

    private final String inputString;

    FieldType(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
