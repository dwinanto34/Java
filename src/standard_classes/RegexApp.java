package standard_classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexApp {
    public static void main(String[] args) {
        String name = "name";
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(name);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
