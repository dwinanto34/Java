package oop.final_classes;

class Vehicle {
    String vehicleType;

    final void drive() {}
    void maintenance() {}
}

final class Car extends Vehicle {
    // Can't override drive because drive is a final method
    // @Override
    // void drive() {}

    @Override
    void maintenance() {
        final String maintenanceType = "Power steering fluid";
        // error because final variable can't be changed
        // maintenanceType = "Transmission fluid";
        
        System.out.println(maintenanceType);
    }
}

// Honda can't extend car class anymore
// Why? Because Car is a final class
// class Honda extends Car {}
