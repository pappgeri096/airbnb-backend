package com.codecool.airbnbmanager.configuration;

import com.codecool.airbnbmanager.model.*;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.api.UserServiceREST;
import com.codecool.airbnbmanager.util.LodgingsType;
import com.codecool.airbnbmanager.util.PasswordHashing;
import com.codecool.airbnbmanager.util.UserFactory;
import com.codecool.airbnbmanager.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    private static final String GUEST_EMAIL = "guest@fakedomain.com";

    private final UserServiceREST userServiceREST;
    private final LodgingsService lodgingsService;

    @Autowired
    public Initializer(UserServiceREST userServiceREST, LodgingsService lodgingsService) {
        this.userServiceREST = userServiceREST;
        this.lodgingsService = lodgingsService;
        init();
    }


    // initialize model objects for testing todo: dele later

    public void init() {
        AddressBuilder fullAddressLL = new AddressBuilder(
                "Country",
                "City",
                "H-34234",
                "1. Street"
        );

        User testLandlord = UserFactory.createUserInstanceBy(
                UserType.LANDLORD,
                "Attila",
                "Kincsei",
                "akincsei@gmail.com",
                "+23123123123",
                PasswordHashing.hashPassword("Qq111111"),
                fullAddressLL
        );

        userServiceREST.add(testLandlord);

        AddressBuilder fullAddressPM = new AddressBuilder(
                "ManCountry",
                "ManCity",
                "M-1001",
                "1. Manager Street"
        );


        User testPropertyManager = UserFactory.createUserInstanceBy(
                UserType.PROPERTY_MANAGER,
                "Hugo",
                "Menedzser",
                "menedzser@gmail.com",
                "+10000000000",
                PasswordHashing.hashPassword("Qq111111"),
                fullAddressPM
        );

        userServiceREST.add(testPropertyManager);

        AddressBuilder fullAddress0 = new AddressBuilder("Molvania", "Molvania City", "MO-2342", "111. Very Nice Street");

        Lodgings newLodging = new Lodgings(
                "My little apartment",
                LodgingsType.ROOM,
                100L,
                10L,
                20L,
                15L,
                4L,
                testLandlord,
                testPropertyManager,
                fullAddress0
        );

        lodgingsService.add(newLodging);

        AddressBuilder fullAddress = new AddressBuilder("Vanuatu", "Big City", "VAU-2342", "111. dfdfce Street");

        Lodgings newLodging2 = new Lodgings(
                "My very big apartment",
                LodgingsType.APARTMENT,
                10033L,
                103L,
                203L,
                153L,
                433L,
                testLandlord,
                fullAddress
        );

        lodgingsService.add(newLodging2);

        AddressBuilder fullAddressGuest = new AddressBuilder(
                "Country",
                "City",
                "W-1111",
                "1. Street"
        );

        User guestUser = UserFactory.createUserInstanceBy(
                UserType.GUEST,
                "Guest",
                "User",
                GUEST_EMAIL,
                "+2211111111",
                PasswordHashing.hashPassword("11111111"),
                fullAddressGuest
        );

        userServiceREST.add(guestUser);

    }

}