package com.learning.java;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
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

    private static void dateFormatter() throws ParseException {
        // Create a SimpleDateFormat instance with a specific pattern.
        // Reference: https://docs.oracle.com/javase/jp/8/docs/api/java/text/SimpleDateFormat.html
        String datePattern = "EEEE dd MMMM yyyy";

        // Format the current date using the default locale
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        String format = sdf.format(new Date());
        System.out.println(format); // Example output: Thursday 05 December 2024

        // Format the current date using the Indonesian locale
        Locale indonesia = new Locale("id", "ID");
        SimpleDateFormat indonesiaSdf = new SimpleDateFormat(datePattern, indonesia);
        String indonesiaFormat = indonesiaSdf.format(new Date());
        System.out.println(indonesiaFormat); // Example output: Kamis 05 Desember 2024

        // Parse a date string into a Date object using the Indonesian locale
        Date date = indonesiaSdf.parse("Kamis 05 Desember 2024");
        System.out.println(date);
    }

    private static void numberFormatter() throws ParseException {
        // Format a number into a string with the default locale (e.g., en_US).
        // Example: 1,000,000.255 (comma as the thousands separator, period as the decimal separator)
        NumberFormat numberFormat = NumberFormat.getInstance();
        String format = numberFormat.format(1000000.255); // output: 1,000,000.255
        System.out.println(format);

        // Use a specific locale (e.g., Indonesian) to format numbers.
        // Example: 1.000.000,255 (period as the thousands separator, comma as the decimal separator)
        Locale indonesia = new Locale("id", "ID");
        NumberFormat indonesiaNumberFormat = NumberFormat.getInstance(indonesia);
        String indonesiaFormat = indonesiaNumberFormat.format(1000000.255); // output: 1.000.000,255
        System.out.println(indonesiaFormat);

        // Parse a formatted string back into a Number object using the same locale.
        Number num = indonesiaNumberFormat.parse("1.000.000,255");
        System.out.println(num.doubleValue()); // output: 1000000.255
        System.out.println(num.longValue()); // output: 1000000
    }

    private static void currency() throws ParseException {
        // Reference: https://www.iso.org/iso-4217-currency-codes.html
        Locale indonesia = new Locale("id", "ID");
        Currency currency = Currency.getInstance(indonesia);
        System.out.println(currency.getDisplayName()); // output: Indonesian Rupiah
        System.out.println(currency.getCurrencyCode()); // output: IDR

        // Create a currency-specific NumberFormat instance for the Indonesian locale.
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indonesia);
        String format = numberFormat.format(1000000.255);
        System.out.println(format); // output: Rp1.000.000,26

        // Parse a formatted currency string back into a numeric value.
        Number num = numberFormat.parse("Rp1.000.000,26");
        System.out.println(num.doubleValue()); // output: 1000000.26
        System.out.println(num.longValue()); // output: 1000000
    }

    public static void run() throws ParseException {
        locale();
        resourceBundle();
        dateFormatter();
        numberFormatter();
        currency();
    }
}
