package oop.fundamental;

import java.util.Objects;

// Product is a class
// Class is a blueprint or prototype to create objects
public class Product {
    // fields
    // Best practice : set the access level to private and create setter and getter for the fields (encapsulation)
    String name;
    String description;
    double price;

    // below field implements encapsulation
    private int weight;

    // default constructor
    public Product() {
    }

    // constructor
    public Product(String name, String description, double price, int weight) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
    }

    // constructor overloading
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // getter for weight field
    public int getWeight() {
        return weight;
    }

    // setter for weight field
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // methods
    public void printDetails() {
        System.out.println("This is product, the product name is " + name +
                " and the description is " + description +
                " and the price is " + price +
                " and the weight is" + weight);
    }

    // override the toString method
    // because the default toString method will only print memory location
    // System.out.println(product);
    // "oop.fundamental.Product@133314b"
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }

    // By default, equals method will compare 2 objects and see if they are in the same memory location
    // Product p1 = new Product("Nokia", "Nokia");
    // Product p2 = new Product("Nokia", "Nokia");
    // p1 and p2 are considered as not equal
    // That's why we need to override the equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && weight == product.weight && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    // System.out.println(p1.hashCode());
    // System.out.println(p2.hashCode());
    // both hashCode functions return the same hash code
    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, weight);
    }
}
