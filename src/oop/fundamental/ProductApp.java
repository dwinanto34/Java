package oop.fundamental;

import java.util.Date;

public class ProductApp {
    public static void main(String[] args) {
        basic();
        inheritance();
        polymorphism();
    }

    private static void basic() {
        System.out.println("Object, Fields, Setter, and Getter");

        // pocky is an object
        // An object is data which contains fields / methods / behavior
        Product pocky = new Product();

        // set fields without using setter (not recommended)
        pocky.name = "Pocky Strawberry";
        pocky.description = "Pocky Strawberry 15g";
        pocky.price = 1.5d;

        // using setter and getter
        pocky.setWeight(15);
        int weight = pocky.getWeight();
        pocky.printDetails();
    }

    private static void inheritance() {
        // initiate new object using constructor
        Product nokia = new Product("Nokia 3310", "Nokia 3310 2002", 10d, 2000);
        nokia.printDetails();

        RetailProduct kitkat = new RetailProduct("Kitkat Matcha", "Kitkat Matcha 15g", 15d, 20, new Date());
        kitkat.printDetails();
    }

    private static void polymorphism() {
        Product generalProduct = new Product("General", "General");
        generalProduct.printDetails();

        // true
        if (generalProduct instanceof Product) {}
        // false
        if (generalProduct instanceof RetailProduct) {}

        generalProduct = new RetailProduct("Retail", "Retail", 10d, 20, new Date());
        generalProduct.printDetails();

        // true
        if (generalProduct instanceof Product) {}
        // true
        if (generalProduct instanceof RetailProduct) {
            // casting
            RetailProduct rp = (RetailProduct) generalProduct;
            rp.printDetails();
        }
    }
}
