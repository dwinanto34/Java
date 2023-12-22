package basic;

public class Loop {
    public static void main(String[] args) {
        String[] stringArr = {"Hello", "World"};

        // for loop
        for (int i = 0; i < stringArr.length; i++) {
            System.out.println(stringArr[i]);
        }

        for (String s : stringArr) {
            System.out.println(s);
        }

        // do while
        int counter = 0;
        do {
            System.out.println(counter);
            counter++;
        } while (counter <= 10);

        // while
        while (counter <= 20) {
            System.out.println(counter);
            counter++;
        }

        // recursive
        long result = factorial(10);
        System.out.println("Factorial : " + result);

        // break and continue
        for (int i = 0; ; i++) {
            if (i % 2 == 0) {
                // skipping the even numbers
                continue;
            }

            if (i >= 20) {
                // stop the loop once the number reaches 20
                break;
            }

            System.out.println(i);
        }
    }

    private static long factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
