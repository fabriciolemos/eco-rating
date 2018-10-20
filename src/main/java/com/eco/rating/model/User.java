package com.eco.rating.model;

public class User {
    private String name;
    private double rValue;

    public User(String name, double rValue) {
        this.name = name;
        this.rValue = rValue;
    }

    public String getName() {
        return name;
    }

    public double getRValue() {
        return rValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", rValue=" + rValue +
                '}';
    }
}
