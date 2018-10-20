package com.eco.rating.adapter;

import com.eco.rating.application.RatingApplicationService;

import java.util.Scanner;

public class StandardInputListener {

    private final Scanner scanner = new Scanner(System.in);
    private final RatingApplicationService ratingApplicationService = new RatingApplicationService();

    public void processDataInput() {
        String line = scanner.nextLine().trim();

        while (!line.equals("")) {
            String[] strings = line.split("\" ");

            String[] locationInputArray = strings[1].split("/");
            String countryName = this.trimQuotes(locationInputArray[0]);
            String stateName = this.trimQuotes(locationInputArray[1]);
            String cityName = this.trimQuotes(locationInputArray[2]);
            double rValue = Double.parseDouble(strings[2]);
            String userName = strings[0].substring(1);

            ratingApplicationService.addUser(userName, rValue, countryName, stateName, cityName);

            line = scanner.nextLine().trim();
        }
    }

    public void processQueryInput() {
        String line = scanner.nextLine().trim();

        while (!line.equals("")) {
            String[] strings = line.split("\" ");
            String userName = strings[0].substring(1);
            String regionString = strings[1];
            String[] regionArray = regionString.split("/");
            String countryName = trimQuotes(regionArray[0]);
            String stateName = regionArray.length >= 2 ? trimQuotes(regionArray[1]) : null;
            String cityName = regionArray.length >= 3 ? trimQuotes(regionArray[2]) : null;

            int rating = this.ratingApplicationService.getRating(userName, countryName, stateName, cityName);

            System.out.println(new StringBuilder("\"").append(userName).append("\" ").append(regionString).append(" ").append(rating));

            line = scanner.nextLine().trim();
        }
    }

    private String trimQuotes(String s) {
        return s.replaceAll("^\"|\"$", "");
    }
}
