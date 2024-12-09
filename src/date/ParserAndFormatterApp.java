package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParserAndFormatterApp {
    public static void run() {
        System.out.println("\n\nParser and Formatter");
        parser();
        formatter();
    }

    private static void parser() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate localDate = LocalDate.parse("2020 10 25", dtf);
        System.out.println(localDate); // Output: 2020-10-25
    }

    private static void formatter() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate localDate = LocalDate.parse("2020 10 25", dtf);
        String format = localDate.format(dtf);
        System.out.println(format); // Output: 2020 10 25
    }
}
