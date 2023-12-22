package oop.static_demo;

public class MathUtil {
    public static int sum(int... numbers) {
        int total = 0;

        for (int number : numbers) {
            total = total + number;
        }

        return total;
    }
}
