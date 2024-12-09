package date;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConversionFromLegacyLibraryToDateTimeApp {
    public static void run() {
        System.out.println("\n\nConversion from legacy library to date time API");

        fromLegacy();
        toLegacy();
    }

    private static void fromLegacy() {
        // Legacy library
        Date legacyDate = new Date();
        Calendar legacyCalendar = Calendar.getInstance();

        // Convert to instant first
        Instant instantDate = legacyDate.toInstant();
        Instant instantCalendar = legacyCalendar.toInstant();

        // Convert to new library
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.ofInstant(instantDate, defaultZoneId);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instantCalendar, defaultZoneId);

        System.out.println(localDate);
        System.out.println(localDateTime);
    }

    private static void toLegacy() {
        // Date time API
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        // Convert to instant first
        Instant instantDate = zonedDateTime.toInstant();

        // Convert to legacy library
        Date date = Date.from(instantDate);
        Calendar calendar = GregorianCalendar.from(zonedDateTime);

        System.out.println(date);
        System.out.println(calendar);
    }
}
