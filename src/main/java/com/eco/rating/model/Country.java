package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;

public class Country extends Region {
    private String name;
    private Map<String, State> stateMap = new HashMap<>();

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }

    public State getState(String name) {
        return stateMap.get(name);
    }

    public void addState(State state) {
        this.stateMap.put(state.getName(), state);
    }
}
