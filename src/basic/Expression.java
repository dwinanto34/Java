package basic;

public class Expression {
    public static void main(String[] args) {
        System.out.println("Expression");

        // if statement
        String word = "word";
        if (word == "word") {
            System.out.println("The value is word");
        } else if (word == "not word") {
            System.out.println("The value is not word");
        } else {
            System.out.println("The value is " + word);
        }

        // switch statement
        switch (word) {
            case "word":
                System.out.println("The value is word");
                // need to add break statement, otherwise the next condition will be executed
                break;
            case "word1":
                System.out.println("The value is word1");
                break;
            default:
                System.out.println("No conditions were met");
        }

        // ternary operator
        int num = 100;
        boolean even = num % 2 == 0 ? true : false;
    }
}
