package standard_classes;

import java.util.Calendar;
import java.util.Date;

public class DateApp {
    public static void main(String[] args) {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.YEAR, -1);
        calendar.set(Calendar.MARCH, Calendar.JANUARY);

        Date result = calendar.getTime();
    }
}
