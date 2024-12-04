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

    public static void run() {
        locale();
    }
}
