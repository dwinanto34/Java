package oop.static_demo;

public class StaticApp {
    public static void main(String[] args) {
        System.out.println(AppConst.APPLICATION_NAME);
        System.out.println(AppConst.APPLICATION_VERSION);

        int total = MathUtil.sum(1, 2, 3, 4, 5);
        System.out.println(total);
    }
}
