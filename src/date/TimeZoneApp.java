package date;

import java.time.*;
import java.util.Set;

public class TimeZoneApp {
    public static void run() {
        System.out.println("\n\nZone ID");
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println(defaultZoneId); // Output: current time zone

        ZoneId zoneIdGMT = ZoneId.of("GMT");
        System.out.println(zoneIdGMT); // Output: GMT

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);

        System.out.println("\n\nZone Offset");
        ZoneOffset zoneOffset1 = ZoneOffset.of("+07:00");
        ZoneOffset zoneOffset2 = ZoneOffset.ofHours(-7);
        ZoneOffset zoneOffset3 = ZoneOffset.ofHoursMinutes(5, 30);

        System.out.println(zoneOffset1);
        System.out.println(zoneOffset2);
        System.out.println(zoneOffset3);

        System.out.println("\n\nZoned Date Time");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(zoneIdGMT);
        ZonedDateTime zonedDateTime3 = ZonedDateTime.of(LocalDateTime.now(), zoneOffset1);

        System.out.println(zonedDateTime1); // Ouptut: 2024-12-09T10:43:43.873035+09:00[Asia/Tokyo]
        System.out.println(zonedDateTime2); // Ouptut: 2024-12-09T01:43:43.873060Z[GMT]
        System.out.println(zonedDateTime3); // Ouptut: 2024-12-09T10:43:43.873071+07:00

        zonedDateTime1.withZoneSameLocal(zoneIdGMT);
        zonedDateTime3.withZoneSameInstant(zoneIdGMT);

        System.out.println(zonedDateTime1); // Output: 2024-12-09T10:47:08.596829+09:00[Asia/Tokyo]
        System.out.println(zonedDateTime2); // Output: 2024-12-09T01:47:08.596846Z[GMT]

        System.out.println("\n\nOffsetTime and OffsetDateTime");
        OffsetTime offsetTime1 = OffsetTime.now();
        OffsetTime offsetTime2 = OffsetTime.of(LocalTime.now(), zoneOffset1);

        OffsetDateTime offsetDateTime1 = OffsetDateTime.now();
        OffsetDateTime offsetDateTime2 = OffsetDateTime.of(LocalDateTime.now(), zoneOffset1);

        System.out.println(offsetTime1); // Output: 11:12:00.832636+09:00
        System.out.println(offsetTime2); // Output: 11:12:00.832636+07:00
        System.out.println(offsetDateTime1); // Output: 2024-12-09T11:12:00.832774+09:00
        System.out.println(offsetDateTime2); // Output: 2024-12-09T11:12:00.832774+07:00

        System.out.println("\n\nInstant");
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.ofEpochMilli(System.currentTimeMillis());
        Instant instant3 = instant2.plusMillis(1_000_000);

        System.out.println(instant1); // Output: 2024-12-09T02:25:14.031473Z
        System.out.println(instant2); // Output: 2024-12-09T02:25:14.031Z
        System.out.println(instant3); // Output: 2024-12-09T02:41:54.031Z

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant1, defaultZoneId);
        LocalTime localTime = LocalTime.ofInstant(instant1, defaultZoneId);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant1, defaultZoneId);

        System.out.println(localDateTime); // Output: 2024-12-09T11:25:14.031473
        System.out.println(localTime); // Output: 11:25:14.031473
        System.out.println(zonedDateTime); // Output: 2024-12-09T11:25:14.031473+09:00[Asia/Tokyo]

        Instant instant4 = LocalDateTime.now().toInstant(zoneOffset1);
        Instant instant5 = ZonedDateTime.now().toInstant();
        Instant instant6 = OffsetDateTime.now().toInstant();

        System.out.println(instant4); // Output: 2024-12-09T04:27:28.773427Z
        System.out.println(instant5); // Output: 2024-12-09T02:27:28.773427Z
        System.out.println(instant6); // Output: 2024-12-09T02:27:28.773427Z
    }
}
