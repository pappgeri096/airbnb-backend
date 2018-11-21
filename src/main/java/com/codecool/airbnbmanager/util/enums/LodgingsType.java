package com.codecool.airbnbmanager.util.enums;

public enum LodgingsType {
    APARTMENT("APARTMENT"),
    FAMILY_HOUSE("FAMILY_HOUSE"),
    ROOM("ROOM"),
    PENTHOUSE("PENTHOUSE");

    private final String lodgingsTypeString;

    LodgingsType(String lodgingsTypeString) {
        this.lodgingsTypeString = lodgingsTypeString;
    }

    public String getLodgingsTypeString() {
        return lodgingsTypeString;
    }
}
