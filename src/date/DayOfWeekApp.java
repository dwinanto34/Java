package date;

import java.time.DayOfWeek;

public class DayOfWeekApp {
    public static void run() {
        System.out.println("\n\nDay of Week");
        DayOfWeek dayOfWeek1 = DayOfWeek.SUNDAY;
        DayOfWeek dayOfWeek2 = dayOfWeek1.plus(1);
        DayOfWeek dayOfWeek3 = dayOfWeek1.minus(1);

        System.out.println(dayOfWeek1); // Output: SUNDAY
        System.out.println(dayOfWeek2); // Output: MONDAY
        System.out.println(dayOfWeek3); // Output: SATURDAY
    }
}
