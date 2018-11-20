package com.codecool.airbnbmanager.service.api;

import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.service.UserService;
import com.codecool.airbnbmanager.util.JsonMappingHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPageServiceREST {

    @Autowired
    UserService userService;

    @Autowired
    LodgingsService lodgingsService;




    public JSONArray handleLodgingsWithUserEmail(String userEmail) {

        User user = userService.handleFindUserByEmail(userEmail);
        List<Lodgings> lodgingsList = lodgingsService.findAllLodgingsByUser(user);

        JSONArray jsonArray = new JSONArray();

        for (Lodgings lodgings : lodgingsList) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", lodgings.getId());
            jsonObject.put("name", lodgings.getName());
            jsonObject.put("type", lodgings.getLodgingsType());
            jsonObject.put("id", lodgings.getId());
            jsonObject.put("id", lodgings.getId());
            jsonObject.put("id", lodgings.getId());
            jsonObject.put("id", lodgings.getId());
            jsonObject.put("id", lodgings.getId());
            jsonObject.put("id", lodgings.getId());

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public String handleLodgingsWithUserEmailJackson(String userEmail) {

        User user = userService.handleFindUserByEmail(userEmail);
        List<Lodgings> lodgingsList = lodgingsService.findAllLodgingsByUser(user);

        String userString = JsonMappingHandler.mapObjectToJsonString(user);

        return userString;
    }


}
