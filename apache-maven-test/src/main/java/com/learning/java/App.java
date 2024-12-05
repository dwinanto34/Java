package com.learning.java;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ParseException, JsonProcessingException, IOException {
        System.out.println("Apache Maven Project!");

        // Java Database Connectivity
        JdbcApp.run();

        // Java I18N
        InternationalizationApp.run();

        // Java JSON
        JsonApp.run();

        // Java CSV
        CsvApp.run();
    }
}
