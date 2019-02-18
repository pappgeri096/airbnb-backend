package com.codecool.airbnbmanager.util;

import com.codecool.airbnbmanager.util.enums.LodgingsType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class LodginsTypeConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        String capitalized = text.toUpperCase();
        LodgingsType item = LodgingsType.valueOf(capitalized);
        setValue(item);
    }
}
