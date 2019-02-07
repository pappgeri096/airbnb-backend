package com.codecool.airbnbmanager.util;

import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.util.enums.UserType;

public class UserFactory {

    public static User createUserInstanceBy(
            UserType type,
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String passwordHash,
            AddressBuilder fullAddress
    ) {
        User requiredUser;

        if (type == UserType.LANDLORD) {
            requiredUser = new Landlord(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
        } else if (type == UserType.PROPERTY_MANAGER) {
            requiredUser = new PropertyManager(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
        } else if (type == UserType.TENANT || type == UserType.GUEST) {
            requiredUser = new Tenant(firstName, surname, email, phoneNumber, passwordHash, fullAddress);
        } else {
            //Todo: create logger here instead of sout
            System.out.println("There is no such user type as " + type.toString());
            requiredUser = null;
        }

        return requiredUser;
    }

}
