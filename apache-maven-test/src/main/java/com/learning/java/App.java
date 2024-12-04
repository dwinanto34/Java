package com.learning.java;

import java.text.ParseException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ParseException {
        System.out.println("Apache Maven Project!");

        // Java Database Connectivity
        JdbcApp.run();

        // Java I18N
        InternationalizationApp.run();
    }
}
