package oop.anonymous_classes;

public class AnonymousClass {
    public static void main(String[] args) {
        Animal animal = new Animal() {
            @Override
            public void sayHello() {
                System.out.println("This is anonymous class");
            }
        };

        animal.sayHello();
    }
}
