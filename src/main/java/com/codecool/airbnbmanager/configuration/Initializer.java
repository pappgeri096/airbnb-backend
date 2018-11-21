package com.codecool.airbnbmanager.configuration;

import com.codecool.airbnbmanager.model.*;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.service.LodgingsServiceREST;
import com.codecool.airbnbmanager.service.ToDoServiceREST;
import com.codecool.airbnbmanager.service.UserServiceREST;
import com.codecool.airbnbmanager.util.enums.LodgingsType;
import com.codecool.airbnbmanager.util.PasswordHashing;
import com.codecool.airbnbmanager.util.UserFactory;
import com.codecool.airbnbmanager.util.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Initializer {

    private static final String GUEST_EMAIL = "guest@fakedomain.com";
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final String FAIL_MESSAGE = "FAIL";


    private final UserServiceREST userServiceREST;
    private final LodgingsServiceREST lodgingsServiceREST;
    private final ToDoServiceREST toDoServiceREST;

    @Autowired
    public Initializer(UserServiceREST userServiceREST, LodgingsServiceREST lodgingsServiceREST, ToDoServiceREST toDoServiceREST) {
        this.userServiceREST = userServiceREST;
        this.lodgingsServiceREST = lodgingsServiceREST;
        this.toDoServiceREST = toDoServiceREST;
        init();
    }

    // initialize model objects for testing todo: dele later

    private void init() {
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

        lodgingsServiceREST.handleLodgingsAddition(newLodging);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate1 = "2018-11-30";
        Date date1 = null;
        try {
            date1 = formatter.parse(stringDate1);
        } catch (ParseException e) {
            System.out.println("Cannot parse date1 in initializer");
            e.printStackTrace();
        }
        ToDo toDo1 = new ToDo("New chairs", newLodging, date1, "Buy new chairs for kitchen in IKEA", 30_000L);
//        newLodging.addTodo(toDo1);

        toDoServiceREST.handleToDoSaving(toDo1);

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

        lodgingsServiceREST.handleLodgingsAddition(newLodging2);

        Date date2 = null;
        try {
            date2 = formatter.parse("2018-11-26");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Cannot parse date 2 in initializer");
        }

        ToDo todo2 = new ToDo("Pay bills", newLodging2, date2, "Electricity and gas", 15_683L);
        toDoServiceREST.handleToDoSaving(todo2);

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