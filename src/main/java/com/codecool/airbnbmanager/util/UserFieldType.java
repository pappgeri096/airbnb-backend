package com.codecool.airbnbmanager.util;

public enum UserFieldType {
    ID("id"),
    FIRST_NAME("firstName"),
    SURNAME("surname"),
    FULL_NAME("full_name"),
    USER_TYPE("user_type"),
    EMAIL_ADDRESS("email"),
    PASSWORD_HASH("passwordHash"),
    PHONE_NUMBER("phoneNumber"),
    FULL_ADDRESS("fullAddress"),
    PROPERTY_MANAGER_LODGINGS("propertyManagerLodgings"),
    LANDLORD_LODGINGS("landlordLodgings");

    private final String inputString;

    UserFieldType(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
