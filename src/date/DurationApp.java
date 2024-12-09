package date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DurationApp {
    public static void run() {
        System.out.println("\n\nDuration");
        Duration duration1 = Duration.ofHours(1);
        Duration duration2 = Duration.ofSeconds(10);
        Duration duration3 = Duration.ofMillis(10_000);

        System.out.println(duration1); // Output: PT1H
        System.out.println(duration2); // Output: PT10S
        System.out.println(duration3); // Output: PT10S

        long days = duration1.toDays();
        long hours = duration1.toHours();
        long minutes = duration1.toMinutes();
        long seconds = duration1.toSeconds();

        System.out.println(days); // Output: 0
        System.out.println(hours); // Output: 1
        System.out.println(minutes); // Output: 60
        System.out.println(seconds); // Output: 3600

        LocalDateTime localDateTime = LocalDateTime.now();
        Duration duration4 = Duration.between(LocalTime.of(10, 00), LocalTime.of(11, 00));
        Duration duration5 = Duration.between(localDateTime, localDateTime.plusHours(1));

        System.out.println(duration4); // Output: PT1H
        System.out.println(duration5); // Output: PT1H
    }
}
