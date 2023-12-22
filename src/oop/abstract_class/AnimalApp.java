package oop.abstract_class;

public class AnimalApp {
    public static void main(String[] args) {
        // Cannot initiate new object using abstract class
        // error
        // Animal animal = new Animal();

        Tiger tiger = new Tiger();
        tiger.setName("Tigon");
        tiger.sayHello();
    }
}
