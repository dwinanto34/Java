package date;

public class DateTime {
    public static void main(String[] args) {
        // Legacy library
        LegacyDateCalendar.run();

        // Java Date and Time API was introduced in Java 8
        LocalDateTimeApp.run();
    }
}
