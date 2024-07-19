package io;

import java.io.Serializable;

public class Product implements Serializable {
    // If we want to use ObjectStream, then implement Serializable is a must
    public static final long serialVersionUID = 1L;

    String name;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
