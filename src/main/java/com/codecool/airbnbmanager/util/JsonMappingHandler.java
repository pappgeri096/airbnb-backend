package com.codecool.airbnbmanager.util;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.service.UserServiceREST;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMappingHandler {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);


    public static Map<String, String> convertJsonArraytoMap(String jsonArray) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(jsonArray, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            LOGGER.error("Could not create map from request body\n\n{}", e.toString());
        }
        return map;
    }

    public static <E> String mapJavaObjectListToJsonArray(List<E> list) {
        ObjectMapper objectMapper = new ObjectMapper();

        String listAsJsonString = "";
        try {
            listAsJsonString = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listAsJsonString;
    }

    public static <T> String mapJavaObjectWithoutFields(T object, List<String> fieldList) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode userNode = mapper.convertValue(object, ObjectNode.class).without(fieldList);
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("data", userNode);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

    public static <T> String mapJavaObjectWithFields(T object, List<String> fieldList) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        JsonNode userNode = mapper.convertValue(object, ObjectNode.class).retain(fieldList);
        rootNode.set("data", userNode);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

}
