package com.eco.rating.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountryRepositoryTest {

    private CountryRepository fixture;

    @Before
    public void setUp() {
        fixture = new CountryRepository();
    }

    @Test
    public void addAndGetOneCountry() {
        String countryName = "US";
        assertNull(fixture.get(countryName));

        Country country = new Country(countryName);
        fixture.add(country);

        assertSame(country, fixture.get(countryName));
    }

    @Test
    public void addAndGetTwoCountries() {
        String countryName1 = "US";
        assertNull(fixture.get(countryName1));

        Country country1 = new Country(countryName1);
        fixture.add(country1);

        assertSame(country1, fixture.get(countryName1));

        String countryName2 = "Canada";
        assertNull(fixture.get(countryName2));

        Country country2 = new Country(countryName2);
        fixture.add(country2);

        assertSame(country2, fixture.get(countryName2));
        assertSame(country1, fixture.get(countryName1));
    }

}