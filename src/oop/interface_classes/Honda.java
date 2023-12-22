package oop.interface_classes;

// a child class can implements more than 1 interface class
public class Honda implements Car, Brand {
    @Override
    public String getBrand() {
        return "Honda";
    }

    @Override
    public void drive() {
        System.out.println("Drive using a Honda");
    }

    @Override
    public String getCarType() {
        return "Honda Civic";
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    // optional to override
    // since we already have the default method
    @Override
    public String getDefaultCountryOrigin() {
        return "Europe";
    }
}
