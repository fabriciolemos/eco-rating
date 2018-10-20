package com.eco.rating;

import com.eco.rating.adapter.StandardInputListener;
import com.eco.rating.application.RatingApplicationService;
import com.eco.rating.model.CountryRepository;
import com.eco.rating.model.UserRepository;

import java.io.InputStream;
import java.io.PrintStream;

public class Rating {

    public static void main(String[] args) {
        new Rating().rateThermostatOwners(System.in, System.out, new RatingApplicationService(new UserRepository(), new CountryRepository()));
    }

    public void rateThermostatOwners(InputStream inputStream, PrintStream out, RatingApplicationService ratingApplicationService) {
        StandardInputListener standardInputListener = new StandardInputListener(inputStream, out, ratingApplicationService);
        standardInputListener.processDataInput();
        standardInputListener.processQueryInput();
    }
}
