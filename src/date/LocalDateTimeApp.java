package date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeApp {
    public static void run() {
        localDate();
        localTime();
        localDateTime();
    }

    private static void localDate() {
        // LocalDate is immutable, but it is thread safe
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(1999, Month.NOVEMBER, 11);
        LocalDate localDate3 = LocalDate.parse("1999-11-11");

        System.out.println(localDate1); // Output: current date
        System.out.println(localDate2); // Output: 1999-11-11
        System.out.println(localDate3); // Output: 1999-11-11

        // Immutable
        LocalDate localDateImmutable1 = LocalDate.of(2000, Month.JANUARY, 31);
        LocalDate localDateImmutable2 = localDateImmutable1.withYear(2024);
        System.out.println(localDateImmutable1.getYear()); // Output: 2000
        System.out.println(localDateImmutable2.getYear()); // Output: 2024

        // Manipulations
        LocalDate localDateManipulation = LocalDate.of(2000, Month.JANUARY, 31);
        localDateManipulation = localDateManipulation.plusMonths(1);
        System.out.println(localDateManipulation); // Output: 2000-02-29
        localDateManipulation = localDateManipulation.minusMonths(1);
        System.out.println(localDateManipulation); // Output: 2000-01-29
    }

    private static void localTime() {
        LocalTime localTime1 = LocalTime.now();
        // format: hour, minute, second, nanoOfSecond
        // with hour and minute parameters
        LocalTime localTime2 = LocalTime.of(3, 1);
        // with second, nanoOfSecond optional parameters
        LocalTime localTime3 = LocalTime.of(3, 1, 1, 100);
        LocalTime localTime4 = LocalTime.parse("03:01");
        LocalTime localTime5 = LocalTime.parse("03:01:01.000000100");

        System.out.println(localTime1); // Output: current time
        System.out.println(localTime2); // Output: 03:01
        System.out.println(localTime3); // Output: 03:01:01.000000100
        System.out.println(localTime4); // Output: 03:01
        System.out.println(localTime5); // Output: 03:01:01.000000100

        // Immutable
        LocalTime localTimeImmutable1 = LocalTime.of(3, 0);
        LocalTime localTimeImmutable2 = localTimeImmutable1.withHour(4);
        System.out.println(localTimeImmutable1.getHour()); // Output: 3
        System.out.println(localTimeImmutable2.getHour()); // Output: 4

        // Manipulations
        LocalTime localTimeManipulation = LocalTime.of(3, 0);
        localTimeManipulation = localTimeManipulation.plusHours(2);
        System.out.println(localTimeManipulation); // Output: 05:00
        localTimeManipulation = localTimeManipulation.minusHours(1);
        System.out.println(localTimeManipulation); // Output: 04:00
    }

    private static void localDateTime() {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(1999, Month.NOVEMBER, 11, 3, 0);
        LocalDateTime localDateTime3 = LocalDateTime.parse("1999-11-11T03:00");

        System.out.println(localDateTime1); // Output: current date time
        System.out.println(localDateTime2); // Output: 1999-11-11T03:00
        System.out.println(localDateTime3); // Output: 1999-11-11T03:00

        // Immutable
        LocalDateTime localDateTimeImmutable1 = LocalDateTime.of(1999, Month.NOVEMBER, 11, 3, 0);
        LocalDateTime localDateTimeImmutable2 = localDateTimeImmutable1.withYear(2000);
        System.out.println(localDateTimeImmutable1.getYear()); // Output: 1999
        System.out.println(localDateTimeImmutable2.getYear()); // Output: 2000

        // Manipulations
        LocalDateTime localDateTimeManipulation = LocalDateTime.of(1999, Month.NOVEMBER, 11, 3, 0);
        localDateTimeManipulation = localDateTimeManipulation.plusMonths(1);
        System.out.println(localDateTimeManipulation); // Output: 1999-12-11T03:00
        localDateTimeManipulation = localDateTimeManipulation.minusMonths(2);
        System.out.println(localDateTimeManipulation); // Output: 1999-10-11T03:00
    }
}
