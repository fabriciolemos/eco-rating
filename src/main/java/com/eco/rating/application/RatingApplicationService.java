package com.eco.rating.application;

import com.eco.rating.model.*;

public class RatingApplicationService {

    private UserRepository userRepository;
    private CountryRepository countryRepository;

    public RatingApplicationService(UserRepository userRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
    }

    public void processNewUser(String userName, double rValue, String countryName, String stateName, String cityName) {
        userRepository.add(new User(userName, rValue));

        Country country = updateCountry(rValue, countryName);
        State state = updateState(rValue, stateName, country);
        updateCity(rValue, cityName, state);
    }

    private void updateCity(double rValue, String cityName, State state) {
        City city = state.getCity(cityName);
        if (city == null) {
            city = new City(cityName);
            state.addCity(city);
        }
        city.addRValue(rValue);
    }

    private State updateState(double rValue, String stateName, Country country) {
        State state = country.getState(stateName);
        if (state == null) {
            state = new State(stateName);
            country.addState(state);
        }
        state.addRValue(rValue);
        return state;
    }

    private Country updateCountry(double rValue, String countryName) {
        Country country = countryRepository.get(countryName);
        if (country == null) {
            country = new Country(countryName);
            countryRepository.add(country);
        }
        country.addRValue(rValue);
        return country;
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
