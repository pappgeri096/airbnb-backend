package com.codecool.airbnbmanager.service.api;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.model.builder.AddressBuilder;
import com.codecool.airbnbmanager.repository.UserRepository;
import com.codecool.airbnbmanager.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UserServiceREST {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);

    @Autowired
    private UserRepository userRepository;

    private User handleFindUserById(String id) {
        return userRepository.findById(Long.parseLong(id)).orElse(null);
    }

    private User handleFindUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail);
    }



    public String createJsonStringByAndIncluding(String userEmail, List<String> fieldsToInclude) {

        User user = handleFindUserByEmail(userEmail);

        String lodgingsListWithSomeUserData;

        try {
            lodgingsListWithSomeUserData = JsonMappingHandler.mapJavaObjectWithFields(user, fieldsToInclude);
        } catch (JsonProcessingException e) {
            lodgingsListWithSomeUserData = "{\"data\":\"User not found\"}";
            LOGGER.error("Could not create lodgings list with user data {}", e.toString());
            e.printStackTrace();
        }

        return lodgingsListWithSomeUserData;
    }

    public String createJsonStringByAndExcluding(String userEmail, List<String> fieldsToExclude) {

        User user = handleFindUserByEmail(userEmail);

        String lodgingsListWithSomeUserData;

        try {
            lodgingsListWithSomeUserData = JsonMappingHandler.mapJavaObjectWithoutFields(user, fieldsToExclude);
        } catch (JsonProcessingException e) {
            lodgingsListWithSomeUserData = "{\"data\":\"User not found\"}";
            LOGGER.error("Could not create lodgings list with user data {}", e.toString());
            e.printStackTrace();
        }

        return lodgingsListWithSomeUserData;
    }


    public boolean handleUserUpdate(String userData) {

        boolean isUpdateSuccessful = false;

        Map<String, String> map = JsonMappingHandler.convertJsonArraytoMap(userData);
        if (map.isEmpty()) {
            return isUpdateSuccessful;
        }

        User user = handleFindUserByEmail(map.get(UserFieldType.EMAIL_ADDRESS.getInputString()));
        String passwordHash = user.getPasswordHash();
        boolean isPasswordCorrect = PasswordHashing.checkPassword(map.get(UserFieldType.OLD_PASSWORD.getInputString()), passwordHash);

        if (isPasswordCorrect) {
            user.setFirstName(map.get(UserFieldType.FIRST_NAME.getInputString()));
            user.setSurname(map.get(UserFieldType.SURNAME.getInputString()));
            user.setPhoneNumber(map.get(UserFieldType.PHONE_NUMBER.getInputString()));
            user.setCountry(map.get(UserFieldType.COUNTRY.getInputString()));
            user.setCity(map.get(UserFieldType.CITY.getInputString()));
            user.setZipCode(map.get(UserFieldType.ZIP_CODE.getInputString()));
            user.setAddress(map.get(UserFieldType.ADDRESS.getInputString()));


            String newPassword = map.get(UserFieldType.NEW_PASSWORD.getInputString());
            boolean isPasswordUpdated = !newPassword.equals("");

            if (isPasswordUpdated) {
                user.setPasswordHash(PasswordHashing.hashPassword(newPassword));
            }

            userRepository.save(user);
            isUpdateSuccessful = true;

        }

        return isUpdateSuccessful;
    }

    public boolean handleUserDeletionBy(String userData) {

        boolean isDeletionSuccessful = false;

        Map<String, String> map = JsonMappingHandler.convertJsonArraytoMap(userData);

        if (map.isEmpty()) {
            return isDeletionSuccessful;
        }

        User user = handleFindUserById(map.get(UserFieldType.ID.getInputString()));

        if (user != null) {
            userRepository.delete(user);
            isDeletionSuccessful = true;
        }
        return isDeletionSuccessful;
    }

    public boolean handleUserAddition(String userData) {

        boolean isAdditionSuccessful = false;

        Map<String, String> map = JsonMappingHandler.convertJsonArraytoMap(userData);

        if (map.isEmpty()) {
            return isAdditionSuccessful;
        }

        String password = map.get(UserFieldType.PASSWORD.getInputString());
        String passwordConfirmation = map.get(UserFieldType.PASSWORD_CONFIRMATION.getInputString());

        if (password.equals(passwordConfirmation)) {

            AddressBuilder fullAddress = new AddressBuilder(
                    map.get(UserFieldType.COUNTRY.getInputString()),
                    map.get(UserFieldType.CITY.getInputString()),
                    map.get(UserFieldType.ZIP_CODE.getInputString()),
                    map.get(UserFieldType.ADDRESS.getInputString())
            );

            User newUser = UserFactory.createUserInstanceBy(
                    UserType.valueOf(map.get(UserFieldType.USER_TYPE.getInputString()).toUpperCase()),
                    map.get(UserFieldType.FIRST_NAME.getInputString()),
                    map.get(UserFieldType.SURNAME.getInputString()),
                    map.get(UserFieldType.EMAIL_ADDRESS.getInputString()),
                    map.get(UserFieldType.PHONE_NUMBER.getInputString()),
                    PasswordHashing.hashPassword(password),
                    fullAddress
            );
            userRepository.save(newUser);
            isAdditionSuccessful = true;

        }

        return isAdditionSuccessful;
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public List<String> getUserFieldTypeEnumAsStringList() {
        return Arrays.stream(UserFieldType.values()).map(UserFieldType::getInputString).collect(Collectors.toList());
    }


}
