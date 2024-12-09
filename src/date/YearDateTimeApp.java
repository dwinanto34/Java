package date;

import java.time.*;

public class YearDateTimeApp {
    public static void run() {
        Year year1 = Year.now();
        Year year2 = Year.of(2000);
        Year year3 = Year.parse("2000");

        YearMonth yearMonth1 = YearMonth.now();
        YearMonth yearMonth2 = YearMonth.of(2000, Month.JANUARY);
        YearMonth yearMonth3 = YearMonth.parse("2000-01");

        MonthDay monthDay1 = MonthDay.now();
        MonthDay monthDay2 = MonthDay.of(Month.JANUARY, 31);
        MonthDay monthDay3 = MonthDay.parse("--01-31");

        LocalDate localDate1 = year1.atMonthDay(monthDay1);
        LocalDate localDate2 = year2.atMonthDay(monthDay2);
        LocalDate localDate3 = year3.atMonthDay(monthDay3);

        System.out.println(localDate1); // Output: current date
        System.out.println(localDate2); // Output: 2000-01-31
        System.out.println(localDate3); // Output: 2000-01-31

        System.out.println(yearMonth1.getYear()); // Output: 2024
        System.out.println(yearMonth2.getMonth()); // Output: JANUARY
        System.out.println(yearMonth3.getMonthValue()); // Output: 1
    }
}
