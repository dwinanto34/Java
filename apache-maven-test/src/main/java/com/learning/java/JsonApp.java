package com.learning.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.learning.java.model.Address;
import com.learning.java.model.Person;

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

    private static void jsonArray() throws JsonProcessingException {
        List<String> products = List.of("Phone", "Bike", "Car");

        // Convert collection to string
        String json = objectMapper.writeValueAsString(products);
        System.out.println(json);

        // Convert string to collection
        List<String> productList = objectMapper.readValue(json, new TypeReference<List<String>>() {});
        System.out.println(productList);
    }

    private static void jsonObjectBean() throws JsonProcessingException {
        Person person = new Person();
        person.setFirstName("Mary");
        person.setLastName("Kristen");
        person.setAge(29);

        Address address = new Address();
        address.setStreet("St. two");
        address.setCity("California");
        address.setCountry("USA");

        person.setAddress(address);

        // Convert person object into string
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);

        // Convert string into person object
        Person maryKristen = objectMapper.readValue(json, Person.class);
        System.out.println(maryKristen);
    }

    private static void mapperFeatures() throws JsonProcessingException {
        // Reference: https://github.com/FasterXML/jackson-databind/wiki/Mapper-Features

        // Configure ObjecMapper to be case-insensitive for property names
        ObjectMapper insensitiveObjectMapper = new ObjectMapper()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        // Represent a person object as a Map with uppercase keys
        Map<String, Object> personMap = Map.of(
            "FIRSTNAME", "John",
            "LASTNAME", "Christian",
            "AGE", 31,
            "ADDRESS", Map.of(
                "STREET", "St. one",
                "CITY", "Zurich",
                "COUNTRY", "Switzerland"
            )
        );

        // Convert the personMap into a JSON string
        // The resulting JSON will have uppercase property names as keys
        String upperCaseJson = objectMapper.writeValueAsString(personMap);
        System.out.println(upperCaseJson);

        // Attempt to deserialize the JSON string back into a Person object
        // The case-insensitive mapper will map uppercase keys in JSON to lowercase fields in the Person class
        Person person = insensitiveObjectMapper.readValue(upperCaseJson, Person.class);
        System.out.println(person);
    }

    private static void deserializationFeatures() throws JsonProcessingException {
        // Reference: https://github.com/FasterXML/jackson-databind/wiki/Deserialization-Features

        // Configure ObjectMapper to ignore unknown properties during deserialization
        ObjectMapper deserializationObjectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Represent a person object as a Map with some unknown keys
        Map<String, Object> personMap = Map.of(
            "firstName", "John",
            "unknownKey", "Hello"
        );

        // Convert the personMap into a JSON string
        String json = objectMapper.writeValueAsString(personMap);
        System.out.println(json);

        // Attempt to deserialize the JSON string back into a Person object
        // The configured ObjectMapper will ignore the unknown "unknownKey"
        Person person = deserializationObjectMapper.readValue(json, Person.class);
        System.out.println(person);
    }

    public static void run() throws JsonProcessingException {
        String json = writeValueAsString();
        readValue(json);
        jsonArray();
        jsonObjectBean();
        mapperFeatures();
        deserializationFeatures();
    }
}
