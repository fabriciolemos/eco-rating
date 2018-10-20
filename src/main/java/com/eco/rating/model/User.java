package com.eco.rating.model;

public class User {
    private String name;
    private City city;
    private double rValue;

    public User(String name, City city, double rValue) {
        this.name = name;
        this.city = city;
        this.rValue = rValue;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public double getRValue() {
        return rValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", city=" + city +
                ", rValue=" + rValue +
                '}';
    }
}
