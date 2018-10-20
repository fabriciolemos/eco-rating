package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(name, state.name) &&
                   Objects.equals(cityMap, state.cityMap) &&
                   Objects.equals(rValues, state.rValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityMap, rValues);
    }

    public City getCity(String name) {
        return cityMap.get(name);
    }

    public void addCity(City city) {
        cityMap.put(city.getName(), city);
    }
}
