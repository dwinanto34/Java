package oop.abstract_class;

// abstract class is only a contract
// all child class that extends this abstract class will have the same attributes
public abstract class Animal {
    // all child class that extends this class will have this field
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // all child class that extends this class will have to implement this method
    // in abstract class we can't define the block method / the implementation
    public abstract void sayHello();
}
