package date;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;

public class TemporalApp {
    public static void run() {
        // Temporal is an interface that defines most of the contracts for the Java Date and Time API.
        System.out.println("\n\nTemporal");

        Temporal temporal1 = LocalDate.now();
        System.out.println(temporal1); // Output: 2024-12-09

        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.with(TemporalAdjusters.firstDayOfMonth());

        System.out.println(localDate1); // Output: 2024-12-09
        System.out.println(localDate2); // Output: 2024-12-01
    }
}
