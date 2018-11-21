package com.codecool.airbnbmanager.util.enums;

public enum UserFieldType {
    ID("id"),
    FIRST_NAME("firstName"),
    SURNAME("surname"),
    FULL_NAME("fullName"),
    USER_TYPE("userType"),
    EMAIL_ADDRESS("email"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("passwordConfirmation"),
    OLD_PASSWORD("oldPassword"),
    NEW_PASSWORD("newPassword"),
    PASSWORD_HASH("passwordHash"),
    PHONE_NUMBER("phoneNumber"),
    COUNTRY("country"),
    CITY("city"),
    ZIP_CODE("zipCode"),
    ADDRESS("address"),
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
