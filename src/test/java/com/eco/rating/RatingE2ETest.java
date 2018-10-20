package com.eco.rating;

import com.eco.rating.application.RatingApplicationService;
import com.eco.rating.model.CountryRepository;
import com.eco.rating.model.UserRepository;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class RatingE2ETest {

    @Test
    public void smokeTestRating() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream expectedFileInputStream = RatingE2ETest.class.getResourceAsStream("e2e-test-input.txt");
        RatingApplicationService applicationService = new RatingApplicationService(new UserRepository(), new CountryRepository());

        new Rating().rateThermostatOwners(expectedFileInputStream, new PrintStream(outputStream), applicationService);

        Scanner expectedResultScanner = new Scanner(RatingE2ETest.class.getResourceAsStream("e2e-test-expected-result.txt")).useDelimiter("\\A");
        assertEquals(expectedResultScanner.next(), outputStream.toString());
    }
}