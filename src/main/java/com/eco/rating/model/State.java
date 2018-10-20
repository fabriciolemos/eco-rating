package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;

public class State extends Region {
    private String name;
    private Country country;
    private Map<String, City> cityMap = new HashMap<>();

    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    public City getCity(String name) {
        return cityMap.get(name);
    }

    public void add(City city) {
        cityMap.put(city.getName(), city);
    }
}
