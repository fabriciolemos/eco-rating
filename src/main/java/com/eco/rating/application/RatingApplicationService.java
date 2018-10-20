package com.eco.rating.application;

import com.eco.rating.model.*;

public class RatingApplicationService {

    private UserRepository userRepository = new UserRepository();
    private CountryRepository countryRepository = new CountryRepository();

    public void addUser(String userName, double rValue, String countryName, String stateName, String cityName) {
        userRepository.add(new User(userName, rValue));

        Country country = countryRepository.get(countryName);
        if (country == null) {
            country = new Country(countryName);
            countryRepository.add(country);
        }
        country.getRValues().add(rValue);

        State state = country.getState(stateName);
        if (state == null) {
            state = new State(stateName);
            country.addState(state);
        }

        state.getRValues().add(rValue);

        City city = state.getCity(cityName);
        if (city == null) {
            city = new City(cityName);
            state.add(city);
        }

        city.getRValues().add(rValue);
    }

    public int getRating(String userName, String countryName, String stateName, String cityName) {
        User user = userRepository.get(userName);
        Country country = countryRepository.get(countryName);

        if (cityName != null) {
            return country.getState(stateName).getCity(cityName).getUserRating(user);
        }

        if (stateName != null) {
            return country.getState(stateName).getUserRating(user);
        }
        return country.getUserRating(user);

    }
}
