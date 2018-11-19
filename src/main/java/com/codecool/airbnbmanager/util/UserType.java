package com.codecool.airbnbmanager.util;

public enum UserType {
    LANDLORD("landlord"),
    PROPERTY_MANAGER("propertyManager"),
    USER_MANAGER("userManager"),
    TENANT("tenant"),
    GUEST("guest");

    private final String stringValue;

    UserType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
