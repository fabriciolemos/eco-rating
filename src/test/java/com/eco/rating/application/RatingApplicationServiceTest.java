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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingApplicationServiceTest {

    private RatingApplicationService fixture;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private Country mockCountry;

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

    @Test
    public void processNewUser_existingCountry_RecordsInserted() {
        Country existingCountry = new Country("Canada");

        State newState = new State("Ontario");
        City newCity = new City("Toronto");

        User user = new User("John", 1.14);

        newState.addRValue(user.getRValue());
        newState.addCity(newCity);

        newCity.addRValue(user.getRValue());

        when(countryRepository.get(existingCountry.getName())).thenReturn(mockCountry);

        fixture.processNewUser(user.getName(), user.getRValue(), existingCountry.getName(), newState.getName() , newCity.getName());

        verify(userRepository).add(user);
        verify(mockCountry).addRValue(user.getRValue());
        verify(mockCountry).addState(newState);
    }
}