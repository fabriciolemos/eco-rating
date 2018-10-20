package com.eco.rating;

import com.eco.rating.adapter.StandardInputListener;
import com.eco.rating.application.RatingApplicationService;

import java.io.InputStream;
import java.io.PrintStream;

public class Rating {

    public static void main(String[] args) {
        new Rating().rateThermostatOwners(System.in, System.out, new RatingApplicationService());
    }

    public void rateThermostatOwners(InputStream inputStream, PrintStream out, RatingApplicationService ratingApplicationService) {
        StandardInputListener standardInputListener = new StandardInputListener(inputStream, out, ratingApplicationService);
        standardInputListener.processDataInput();
        standardInputListener.processQueryInput();
    }
}
