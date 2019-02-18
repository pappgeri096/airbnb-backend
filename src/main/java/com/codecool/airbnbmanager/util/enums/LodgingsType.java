package com.codecool.airbnbmanager.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

    @JsonCreator
    public LodgingsType getEnumFromString(String name){
        for(LodgingsType lodgingsType : LodgingsType.values()){
            if(lodgingsType.lodgingsTypeString.equalsIgnoreCase(name)){
                return lodgingsType;
            }
        }
        return null;
    }

    @JsonValue
    public String getName(){
        return name();
    }



}
