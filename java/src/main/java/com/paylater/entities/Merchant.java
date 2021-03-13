package com.paylater.entities;

public class Merchant {
    private final String name;
    private final String email;
    private double discountPercentage;
    private double discountReceived;

    public Merchant(String name, String email, String discountPercentage) {
        this.name = name;
        this.email = email;
        this.discountPercentage = Double.parseDouble((discountPercentage.split("%"))[0]);
        this.discountReceived = 0;
    }

    @Override
    public String toString() {
        return name + "(" + discountPercentage + "%)";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountReceived() {
        return discountReceived;
    }

    public void setDiscountReceived(double discountReceived) {
        this.discountReceived = discountReceived;
    }
}
