package oop.interface_classes;

public interface Brand {
    String getBrand();

    // default method
    // so the child class who implements this class don't have to override the method
    // and could use the default method
    default String getDefaultCountryOrigin() {
        return "Japan";
    }
}
