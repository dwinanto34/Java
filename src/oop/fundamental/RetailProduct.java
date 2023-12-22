package oop.fundamental;

import java.util.Date;

// using "extends" keyword to inherit from the Product class
// a child class only can inherit from one parent class
public class RetailProduct extends Product {
    // access level
    // CLASS, PACKAGE, SUBCLASS, WORLD -> public
    // CLASS, PACKAGE, SUBCLASS -> protected
    // CLASS, PACKAGE -> no modifier
    // CLASS -> private
    Date expiredDate;

    public RetailProduct() {
    }

    public RetailProduct(String name, String description, double price, int weight, Date expiredDate) {
        // "super" keyword refers to parent object
        // in this case, we want to access the constructor of the parent class
        super(name, description, price, weight);
        this.expiredDate = expiredDate;
    }

    // always use @Override method when override parent class method
    // Because the compiler will detect typo and signature compatibility
    @Override
    public void printDetails() {
        System.out.println("This is RETAIL product, the product name is " + name +
                " and the description is " + description +
                " and the price is " + price +
                " and the weight is" + super.getWeight()+
                " and the expired date is " + expiredDate);
    }

    // generate toString hashCode and equals
}
