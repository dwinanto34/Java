package oop.enums;

public class OrderStatusApp {
    public static void main(String[] args) {
        OrderStatus orderStatus1 = OrderStatus.DELIVERED;
        OrderStatus orderStatus2 = OrderStatus.valueOf("DELIVERED");
        OrderStatus orderStatus3 = OrderStatus.findByDescription("Delivered");
        OrderStatus orderStatus4 = OrderStatus.findByName("DELIVERED");

        OrderStatus[] orderStatuses = OrderStatus.values();

        String orderStatusName = OrderStatus.DELIVERED.name();
        String orderStatusDescription = OrderStatus.DELIVERED.getDescription();
    }
}
