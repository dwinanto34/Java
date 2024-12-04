package com.learning.java;

import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationApp {
    private static void locale() {
        // Lang (ISO 639-1 language codes)
        // Reference: https://www.loc.gov/standards/iso639-2/php/code_list.php

        // Countries (ISO 3166-1 alpha-2 country codes)
        // Reference: https://www.iban.com/country-codes

        String language = "id";
        String country = "ID";
        Locale locale = new Locale(language, country);

        System.out.println(locale.getLanguage());
        System.out.println(locale.getDisplayLanguage());

        System.out.println(locale.getCountry());
        System.out.println(locale.getDisplayCountry());
    }

    private static void resourceBundle() {
        // Access values from the default properties file (message.properties)
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
        System.out.println(resourceBundle.getString("hello"));

        // Access the resource bundle for the Indonesian locale (message_id_ID.properties).
        // <filename>_<language>_<country>.properties
        Locale indonesia = new Locale("id", "ID");
        ResourceBundle resourceBundleIndonesia = ResourceBundle.getBundle("message", indonesia);
        System.out.println(resourceBundleIndonesia.getString("hello"));

        // Access the resource bundle for the US locale (message_en_US.properties).
        // If the specific file does not exist, the default file (message.properties) will be used.
        Locale unitedStates = new Locale("en", "US");
        ResourceBundle resourceBundleUS = ResourceBundle.getBundle("message", unitedStates);
        System.out.println(resourceBundleUS.getString("hello"));
    }

    public static void run() {
        locale();
        resourceBundle();
    }
}
