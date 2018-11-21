package com.codecool.airbnbmanager.model;


import com.codecool.airbnbmanager.model.builder.AddressBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "LANDLORD")
public class Landlord extends User {


    public Landlord() {
    }

    public Landlord(
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
