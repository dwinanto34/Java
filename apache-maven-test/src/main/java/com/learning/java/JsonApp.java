package com.learning.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonApp {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static String writeValueAsString() throws JsonProcessingException {
        // Represent an object using map
        Map<String, Object> person = Map.of(
                "firstName", "John",
                "lastName", "Christian",
                "age", 31,
                "address", Map.of(
                        "street", "St. one",
                        "city", "Zurich",
                        "country", "Switzerland"
                )
        );

        // Write map object into string
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);

        return json;
    }

    private static void readValue(String json) throws JsonProcessingException {
        // Convert string into map object
        Map<String, Object> person = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        System.out.println(person.get("firstName"));
        System.out.println(person.get("lastName"));
        System.out.println(person.get("age"));
        System.out.println(person.get("address"));
    }

    public static void run() throws JsonProcessingException {
        String json = writeValueAsString();
        readValue(json);
    }
}
