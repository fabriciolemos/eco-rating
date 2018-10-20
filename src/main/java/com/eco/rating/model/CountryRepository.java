package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;

public class CountryRepository {

    private Map<String, Country> countryMap = new HashMap<>();

    public Country get(String name) {
        return countryMap.get(name);
    }

    public void add(Country country) {
        countryMap.put(country.getName(), country);
    }
}
