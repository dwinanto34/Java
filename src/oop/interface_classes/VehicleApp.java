package oop.interface_classes;

public class VehicleApp {
    public static void main(String[] args) {
        Honda honda = new Honda();
        honda.drive();

        String vehicleType = honda.getVehicleType();
        System.out.println(vehicleType);

        String carType = honda.getCarType();
        System.out.println(carType);

        String brand = honda.getBrand();
        System.out.println(brand);

        String countryOrigin = honda.getDefaultCountryOrigin();
        System.out.println(countryOrigin);
    }
}
