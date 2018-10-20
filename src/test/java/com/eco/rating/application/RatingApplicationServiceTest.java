package com.eco.rating.application;

import com.eco.rating.model.City;
import com.eco.rating.model.Country;
import com.eco.rating.model.CountryRepository;
import com.eco.rating.model.State;
import com.eco.rating.model.User;
import com.eco.rating.model.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RatingApplicationServiceTest {

    private RatingApplicationService fixture;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CountryRepository countryRepository;

    @Before
    public void setUp() {
        fixture = new RatingApplicationService(userRepository, countryRepository);
    }

    @Test
    public void processNewUser_emptyRepos_RecordsInserted() {
        User user = new User("John", 1.14);

        Country country = new Country("Canada");
        country.addRValue(user.getRValue());

        State state = new State("Ontario");
        state.addRValue(user.getRValue());
        country.addState(state);

        City city = new City("Toronto");
        city.addRValue(user.getRValue());
        state.addCity(city);

        fixture.processNewUser(user.getName(), user.getRValue(), country.getName(), state.getName(), city.getName());

        verify(userRepository).add(user);
        verify(countryRepository).add(country);
    }
}