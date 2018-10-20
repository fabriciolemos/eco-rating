package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;

public class State extends Region {
    private String name;
    private Map<String, City> cityMap = new HashMap<>();

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                '}';
    }

    public City getCity(String name) {
        return cityMap.get(name);
    }

    public void add(City city) {
        cityMap.put(city.getName(), city);
    }
}
