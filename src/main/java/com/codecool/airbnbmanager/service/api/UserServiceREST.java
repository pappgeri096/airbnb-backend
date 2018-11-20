package com.codecool.airbnbmanager.service.api;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import com.codecool.airbnbmanager.util.JsonMappingHandler;
import com.codecool.airbnbmanager.util.UserFieldType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceREST {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);


    @Autowired
    private UserService userService;

    public String createJsonStringByAndIncluding(String userEmail, List<String> fieldsToInclude) {

        User user = userService.handleFindUserByEmail(userEmail);

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

    public List<String> getUserFieldTypeEnumAsStringList() {
        return Arrays.stream(UserFieldType.values()).map(UserFieldType::getInputString).collect(Collectors.toList());
    }



    //    public JSONArray handleLodgingsWithUserEmail(String userEmail) {
//
//        User user = userService.handleFindUserByEmail(userEmail);
//        List<Lodgings> lodgingsList = lodgingsService.findAllLodgingsByUser(user);
//
//        JSONArray jsonArray = new JSONArray();
//
//        for (Lodgings lodgings : lodgingsList) {
//
//            JSONObject jsonObject = new JSONObject();
//
//            jsonObject.put("id", lodgings.getId());
//            jsonObject.put("name", lodgings.getName());
//            jsonObject.put("type", lodgings.getLodgingsType());
//            jsonObject.put("id", lodgings.getId());
//            jsonObject.put("id", lodgings.getId());
//            jsonObject.put("id", lodgings.getId());
//            jsonObject.put("id", lodgings.getId());
//            jsonObject.put("id", lodgings.getId());
//            jsonObject.put("id", lodgings.getId());
//
//            jsonArray.put(jsonObject);
//        }
//        return jsonArray;
//    }


    public String handleRequestForJsonStringFromUserBy(String userEmail) {

        User user = userService.handleFindUserByEmail(userEmail);

        return JsonMappingHandler.mapObjectToJsonString(user);
    }


}
