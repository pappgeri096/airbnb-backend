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

        requiredUser = new User(firstName, surname, email, phoneNumber, passwordHash, fullAddress);

        return requiredUser;
    }

}
