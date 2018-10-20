package com.eco.rating;

import com.eco.rating.application.RatingApplicationService;
import org.junit.jupiter.api.Test;

class RatingE2ETest {

    @Test
    public void smokeTestRating() {
        new Rating().rateThermostatOwners(RatingE2ETest.class.getResourceAsStream("e2e-test-input.txt"), new RatingApplicationService());
    }
}