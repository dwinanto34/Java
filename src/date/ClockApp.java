package date;

import java.time.*;

public class ClockApp {
    public static void run() {
        System.out.println("\n\nClock");
        Clock clockUTC = Clock.systemUTC();
        Clock clockSystem = Clock.systemDefaultZone();
        Clock clockJakarta = Clock.system(ZoneId.of("Asia/Jakarta"));

        System.out.println(clockUTC); // Output: SystemClock[Z]
        System.out.println(clockSystem); // Output: SystemClock[Asia/Tokyo]
        System.out.println(clockJakarta); // Output: SystemClock[Asia/Jakarta]

        Instant instant1 = clockUTC.instant();
        System.out.println(instant1); // Output: 2024-12-09T02:37:16.005222Z

        Year year = Year.now(clockJakarta);
        YearMonth yearMonth = YearMonth.now(clockJakarta);
        LocalTime localTime = LocalTime.now(clockJakarta);
        LocalDate localDate = LocalDate.now(clockJakarta);
        LocalDateTime localDateTime = LocalDateTime.now(clockJakarta);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(clockJakarta);

        System.out.println(year); // Output: 2024
        System.out.println(yearMonth); // Output: 2024-12
        System.out.println(localTime); // Output: 09:39:39.509454
        System.out.println(localDate); // Output: 2024-12-09
        System.out.println(localDateTime); // Output: 2024-12-09T09:39:39.509458
        System.out.println(zonedDateTime); // Output: 2024-12-09T09:39:39.509460+07:00[Asia/Jakarta]
    }
}
