package com.eco.rating.model;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Double.compare(user.rValue, rValue) == 0 &&
                   Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rValue);
    }
}
