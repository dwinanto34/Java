package oop.enums;

import java.util.Arrays;

public enum OrderStatus {
    WAITING_FOR_PAYMENT("Waiting for payment"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus findByDescription(String description) {
        return Arrays.stream(values())
                .filter(status -> status.description.equals(description))
                .findFirst()
                .orElse(null);
    }

    public static OrderStatus findByName(String name) {
        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
