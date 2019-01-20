package com.codecool.airbnbmanager.model;

import com.codecool.airbnbmanager.model.builder.AddressBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TENANT")
public class Tenant extends User {


    public Tenant() {
    }

    public Tenant(
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        super(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
