package date;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class LegacyDateCalendar {
    // Legacy library using java.util.Date and java.util.Calendar
    // Problems with these libraries:
    // - Not thread-safe
    // - Challenging for date modification operations
    // - Timezone issues

    // NOTE: Most functions in java.util.Date are deprecated, and java.util.Calendar is suggested as a replacement.

    // References:
    // https://docs.oracle.com/javase/jp/8/docs/api/java/util/Date.html
    // https://docs.oracle.com/javase/jp/8/docs/api/java/util/Calendar.html
    public static void run() {
        date();
        calendar();
        timeZone();
    }

    private static void date() {
        System.out.println("\n\njava.util.Date");

        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis());
        Date date3 = new Date(942246000000L);

        System.out.println(date1); // Output: current time
        System.out.println(date2); // Output: current time
        System.out.println(date3); // Output: 1999-11-11
    }

    private static void calendar() {
        System.out.println("\n\njava.util.Calendar");

        getCalendarCurrentTime();
        calendarOperations();
    }

    private static void timeZone() {
        System.out.println("\n\njava.util.TimeZone");

        TimeZone timeZoneDefault = TimeZone.getDefault();
        System.out.println("Default TimeZone: " + timeZoneDefault.getID());

        TimeZone timeZoneGMT = TimeZone.getTimeZone("GMT");
        System.out.println("GMT TimeZone: " + timeZoneGMT.getID());

        System.out.println("Available TimeZones: " + List.of(TimeZone.getAvailableIDs()));

        Date date = new Date();
        System.out.println("Current date: " + date);
        System.out.println("GMT date (deprecated): " + date.toGMTString());

        Calendar calendarGmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        System.out.println("Current Hour (GMT): " + calendarGmt.get(Calendar.HOUR_OF_DAY));

        Calendar calendarJapan = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println("Current Hour (Tokyo): " + calendarJapan.get(Calendar.HOUR_OF_DAY));
    }

    private static void getCalendarCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();
        System.out.println("Current time using Calendar: " + date); // Output: current time
    }

    private static void calendarOperations() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1999);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);
        calendar.set(Calendar.MILLISECOND, 10);

        Date date = calendar.getTime();
        System.out.println("Set date: " + date); // Output: Thu Nov 11 10:10:10 JST 1999

        System.out.println("Year: " + calendar.get(Calendar.YEAR)); // Output: 1999
        System.out.println("Month: " + calendar.get(Calendar.MONTH)); // Output: 10 (November)
        System.out.println("Day: " + calendar.get(Calendar.DAY_OF_MONTH)); // Output: 11
        System.out.println("Hour: " + calendar.get(Calendar.HOUR_OF_DAY)); // Output: 10
        System.out.println("Minute: " + calendar.get(Calendar.MINUTE)); // Output: 10
        System.out.println("Second: " + calendar.get(Calendar.SECOND)); // Output: 10
        System.out.println("Millisecond: " + calendar.get(Calendar.MILLISECOND)); // Output: 10
    }
}
