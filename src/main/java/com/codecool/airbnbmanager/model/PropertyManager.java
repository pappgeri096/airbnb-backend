package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.model.builder.AddressBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "PROPERTY_MANAGER")
public class PropertyManager extends User {



    public PropertyManager() {

    }

    public PropertyManager(
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        super(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
    }

}
