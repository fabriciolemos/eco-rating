package com.eco.rating.model;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.stream;
import static org.junit.Assert.assertEquals;

public class RegionTest {

    private Region fixture;

    @Before
    public void setUp() {
        fixture = new Country("Canada");
    }

    @Test
    public void getRating_UniqueRValue_LowRating() {
        this.addRValuesToFixture(1.5, 3.7, 2.11, 2.11, 1.43);

        assertEquals(4, fixture.getRValueRating(1.5));
    }

    @Test
    public void getRating_NonUniqueRValue_HighRating() {
        this.addRValuesToFixture(1.5, 3.7, 2.11, 2.11, 1.43);

        assertEquals(8, fixture.getRValueRating(2.11));
    }

    @Test
    public void getRating_EvenNumberOfRValues_HighRating() {
        this.addRValuesToFixture(1.5, 4.3, 2.1, 9.7, 4.5, 10.7);

        assertEquals(7, fixture.getRValueRating(4.5));
    }

    @Test
    public void getRating_AllIdenticalRValues_MaxRating() {
        this.addRValuesToFixture(1.5, 1.5, 1.5);

        assertEquals(10, fixture.getRValueRating(1.5));
    }

    @Test
    public void getRating_UnitaryCollection_MaxRating() {
        this.addRValuesToFixture(5.532);

        assertEquals(10, fixture.getRValueRating(5.532));
    }

    @Test
    public void getRating_LargeNumberOfRValues_HighRating() {
        this.addRValuesToFixture(11.5, 4.36, 21.001, 9.7, 4.5, 10.7, 0.0, 50.0, 43.214, 30.001, 37.321, 33.333, 10.0);

        assertEquals(7, fixture.getRValueRating(21.001));
    }

    @Test
    public void getRating_LargeNumberOfRValues_MaxRating() {
        this.addRValuesToFixture(11.5, 4.36, 21.001, 9.7, 4.5, 10.7, 0.0, 50.0, 43.214, 30.001, 37.321, 33.333, 10.0);

        assertEquals(10, fixture.getRValueRating(50.0));
    }

    @Test
    public void getRating_LargeNumberOfRValues_MinRating() {
        this.addRValuesToFixture(11.5, 4.36, 21.001, 9.7, 4.5, 10.7, 0.0, 50.0, 43.214, 30.001, 37.321, 33.333, 10.0);

        assertEquals(1, fixture.getRValueRating(0.0));
    }

    private void addRValuesToFixture(double... rValues) {
        stream(rValues).forEach(rValue -> fixture.addRValue(rValue));
    }
}