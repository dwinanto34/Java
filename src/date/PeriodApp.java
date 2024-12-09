package date;

import java.time.LocalDate;
import java.time.Period;

public class PeriodApp {
    public static void run() {
        // Period is similar to Duration.
        // The key difference is that Period is typically used for date-based values.
        System.out.println("\n\nPeriod");
        Period period1 = Period.ofMonths(1);
        Period period2 = Period.ofWeeks(1);
        Period period3 = Period.ofDays(1);
        Period period4 = Period.of(10, 10, 10);

        System.out.println(period1); // Output: P1M
        System.out.println(period2); // Output: P7D
        System.out.println(period3); // Output: P1D
        System.out.println(period4); // Output: P10Y10M10D

        System.out.println(period4.getYears()); // Output: 10
        System.out.println(period4.getMonths()); // Output: 10
        System.out.println(period4.getDays()); // Output: 10

        Period period5 = Period.between(LocalDate.of(1945, 8, 17), LocalDate.now());
        System.out.println(period5); // Output: P79Y3M22D
    }
}