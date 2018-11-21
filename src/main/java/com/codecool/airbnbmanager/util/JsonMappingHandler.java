package com.codecool.airbnbmanager.util;

import ch.qos.logback.classic.Logger;
import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.api.UserServiceREST;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import jdk.nashorn.internal.runtime.PropertyAccess;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMappingHandler {

    private static final Logger LOGGER = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(UserServiceREST.class);

    // From Java objects to JSON

    public static <T> String mapObjectToJsonString(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String objectAsString = "";
        try {
            objectAsString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectAsString;
    }

    public static <T> void writeObjectToJsonFile(String fileName, T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new FileOutputStream(fileName), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <E> String writeListToJsonString(List<E> list) {
        ObjectMapper objectMapper = new ObjectMapper();

        String listAsJsonString = "";
        try {
            listAsJsonString = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listAsJsonString;
    }

    public static void writeListToJsonFile(String filePath, List list) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static <K, V> String writeMapToJsonString(Map<K, V> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String carAsString = "";
        try {
            carAsString = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return carAsString;
    }

    /**
     * writes cart data to json file
     * creates UUID for each order and gives the file this UUID as name
     *
     * @param filePath
     * @param mapObject
     *
     * */

    public static <T, K, V> void writeMapToFile(String filePath, Map<K, V> mapObject) {

        ObjectMapper orderMapper = new ObjectMapper();
        try {
            orderMapper.writeValue(new FileOutputStream(filePath), mapObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> convertJsonArraytoMap(String jsonArray) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(jsonArray, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            LOGGER.error("Could not create map from request body {}", e.toString());
        }
        return map;
    }

    // JSON TO JAVA OBJECTS

    protected static <T> T readJsonArrayToObject(String jsonString, Class<T> className) {

        ObjectMapper objectMapper = new ObjectMapper();

        T someObject = null;
        try {
            someObject = objectMapper.readValue(jsonString, className);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return someObject;
    }

    public static <T> T readJsonFileToObject(String pathName, Class<T> className) {

        ObjectMapper objectMapper = new ObjectMapper();

        T someObject = null;
        try {
            someObject = objectMapper.readValue(new File(pathName), className);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return someObject;
    }

    protected static <E> List<E> readJsonArrayToObjectList(String jsonArray, Class<E> className) {

        ObjectMapper objectMapper = new ObjectMapper();

        List<E> objectList = null;
        try {
            objectList = objectMapper.readValue(jsonArray, new TypeReference<List<E>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectList;
    }

    public static <E> List<E> readJsonFileToObjectList(String fileName, E className) {

        ObjectMapper objectMapper = new ObjectMapper();

        List<E> objectList = null;
        try {
            objectList = objectMapper.readValue(new File(fileName), new TypeReference<List<E>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectList;
    }

    // todo: new!!
    public static List<?> readJsonFileToObjectList(String fileName, String fullyQualifiedName) throws ClassNotFoundException {

        Class<?> aClass = Class.forName(fullyQualifiedName);
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, aClass);

        List<?> objectList = Collections.singletonList(listType);

        try {
            objectList = objectMapper.readValue(new File(fileName), listType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectList;
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


    // todo: json practice
//    public static <T> String objectNodeAndJsonNode(T user, List<Lodgings> lodgingsList) throws JsonProcessingException {
//
//        String lodgingsString = writeListToJsonString(lodgingsList);
//
//        String userAllData = mapObjectToJsonString(user);
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectNode rootNode = mapper.createObjectNode();
//
//        rootNode.put("lodgingsList", lodgingsString);
//
//        ObjectNode userNode = mapper.createObjectNode();
//        userNode.put("user", userAllData).without("passwordHash");
//
//        rootNode.put("userData", userAllData).without("passwordHash");
//
//        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
//
//        return jsonString;
//    }


}
