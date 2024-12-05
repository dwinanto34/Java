package lombok;

import oop.exceptions.ValidationUtil;

public class LombokApp {
    public static void main(String[] args) {
        // default constructor
        Product retailProduct = new Product();

        // setter
        retailProduct.setName("name");
        retailProduct.setDescription("description");

        // getter
        retailProduct.getName();
        retailProduct.getDescription();

        // all fields constructor
        Product electronicProduct = new Product("name", "description");

        // toString()
        String electronicProductString = electronicProduct.toString();
        System.out.println(electronicProductString);

        // equals and hash code
        boolean equal = retailProduct.equals(electronicProduct);
        long hashCode1 = retailProduct.hashCode();
        long hashCode2 = electronicProduct.hashCode();

        // builder
        Product car = Product.builder()
                .name("name")
                .description("description")
                .build();

        // with
        // copyOfCar will get a copy of all the fields from car except the value of name
        Product copyOfCar = car.withName("updated-name");

        // result : updated-name
        System.out.println(copyOfCar.getName());
        // result : description
        System.out.println(copyOfCar.getDescription());

        // SneakyThrows
        sneakyThrowsDemo();

        // Synchronized
        LombokApp app = new LombokApp();
        app.decrement();
        app.increment();
    }

    // The @SneakyThrows annotation allows developers to leave checked exception unhandled
    @SneakyThrows
    private static void sneakyThrowsDemo() {
        // below method call might throw checked exception
        // String invalidEmail = "this is not a valid email";
        // ValidationUtil.validateEmailFormat(invalidEmail);
    }

    // Synchronized demo

    private final Object counterLock = new Object();
    private int counter = 0;

    @Synchronized(value = "counterLock")
    private void increment() {
        counter++;
    }

    @Synchronized(value = "counterLock")
    private void decrement() {
        counter--;
    }
}
