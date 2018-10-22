package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Country extends Region {
    private String name;
    private Map<String, State> stateMap = new HashMap<>();

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public State getState(String name) {
        return stateMap.get(name);
    }

    public void addState(State state) {
        this.stateMap.put(state.getName(), state);
    }

    @Override
    public String toString() {
        return "Country{" +
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
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
                   Objects.equals(stateMap, country.stateMap) &&
                   Objects.equals(rValues, country.rValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stateMap, rValues);
    }
}
