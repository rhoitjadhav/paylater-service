package com.paylater.entities;

public class User {
    private final String name;
    private final String email;
    private final int creditLimit;
    private int currentLimit;

    public User(String name, String email, int creditLimit) {
        this.name = name;
        this.email = email;
        this.creditLimit = this.currentLimit = creditLimit;
    }

    @Override
    public String toString() {
        return name + "(" + creditLimit + ")";
    }

    public boolean nameValidation(String name) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean emailValidation(String email) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean creditLimitValidation(int creditLimit) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public int getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(int currentLimit) {
        this.currentLimit = currentLimit;
    }
}
