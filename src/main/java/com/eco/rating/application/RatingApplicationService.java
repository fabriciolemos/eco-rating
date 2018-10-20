package com.eco.rating.application;

import com.eco.rating.adapter.StandardInputListener;
import com.eco.rating.model.*;

import java.util.List;

public class RatingApplicationService {

    private UserRepository userRepository = new UserRepository();
    private CountryRepository countryRepository = new CountryRepository();

    public void solveRating() {
        StandardInputListener standardInputListener = new StandardInputListener();
        List<User> users = standardInputListener.getDataInput();

        for (User user : users) {
            // TODO: need to check for duplicates?
            userRepository.add(user);

            State userState = user.getCity().getState();
            String countryName = userState.getCountry().getName();
            Country country = countryRepository.get(countryName);
            if (country == null) {
                country = new Country(countryName);
                countryRepository.add(country);
            }
            country.getRValues().add(user.getRValue());

            State state = country.getState(userState.getName());
            if (state == null) {
                state = new State(userState.getName(), country);
                country.addState(state);
            }

            state.getRValues().add(user.getRValue());

            City city = state.getCity(user.getCity().getName());
            if (city == null) {
                city = new City(user.getCity().getName(), state);
                state.add(city);
            }

            city.getRValues().add(user.getRValue());

        }
        System.out.println(users);

        List<QueryInput> queryInput = standardInputListener.getQueryInput();
        System.out.println(queryInput);

        for (QueryInput input : queryInput) {
            User user = userRepository.get(input.getUserName());
            Country country = countryRepository.get(input.getCountryName());
            if (input.getCityName() != null) {
                input.setRating(country.getState(input.getStateName()).getCity(input.getCityName()).getUserRating(user));
            } else if (input.getStateName() != null) {
                input.setRating(country.getState(input.getStateName()).getUserRating(user));
            } else {
                input.setRating(country.getUserRating(user));
            }
        }

        System.out.println(queryInput);
    }
}
