package com.eco.rating;

import com.eco.rating.application.RatingApplicationService;
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

        new Rating().rateThermostatOwners(expectedFileInputStream, new PrintStream(outputStream), new RatingApplicationService());

        Scanner expectedResultScanner = new Scanner(RatingE2ETest.class.getResourceAsStream("e2e-test-expected-result.txt")).useDelimiter("\\A");
        assertEquals(expectedResultScanner.next(), outputStream.toString());
    }
}