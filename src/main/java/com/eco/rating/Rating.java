package com.eco.rating;

import com.eco.rating.adapter.StandardInputListener;
import com.eco.rating.application.RatingApplicationService;

import java.io.InputStream;

public class Rating {

    public static void main(String[] args) {
        new Rating().rateThermostatOwners(System.in, new RatingApplicationService());
    }

    public void rateThermostatOwners(InputStream inputStream, RatingApplicationService ratingApplicationService) {
        StandardInputListener standardInputListener = new StandardInputListener(inputStream, ratingApplicationService);
        standardInputListener.processDataInput();
        standardInputListener.processQueryInput();
    }
}
