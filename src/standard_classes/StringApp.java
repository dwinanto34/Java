package standard_classes;

import java.util.StringJoiner;
import java.util.StringTokenizer;

public class StringApp {

    public static void main(String[] args) {
        // 1. String -- Immutable
        String name = "name";
        name.length();
        name.contains("a");
        name.concat(name);
        name.startsWith("a");
        name.toUpperCase();
        name.toLowerCase();
        // and so on

        // 2. StringBuffer -- Mutable -- Thread safe -- Slower
        StringBuffer sBuffer = new StringBuffer(name)
                .append(name);

        // 3. StringBuilder -- Mutable -- Not thread safe -- Faster
        StringBuilder sBuilder = new StringBuilder(name)
                .append(name);

        // 4. String joiner
        StringJoiner joiner = new StringJoiner(
                "||", "[", "]"
        );
        String[] productNames = {"Pocky", "Kitkat"};
        for (String productName : productNames) {
            joiner.add(productName);
        }
        // result: [Pocky||Kitkat]
        System.out.println(joiner.toString());

        // 5. String tokenizer
        String productTokens = "Pocky Kitkat";
        StringTokenizer stringTokenizer = new StringTokenizer(productTokens, " ");

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            System.out.println(token);
        }
    }
}
