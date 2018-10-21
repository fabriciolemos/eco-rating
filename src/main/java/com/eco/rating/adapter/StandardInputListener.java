package com.eco.rating.adapter;

import com.eco.rating.application.RatingApplicationService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class StandardInputListener {

    private static final Pattern QUOTES_PATTERN = Pattern.compile("^\"|\"$");
    private static final Pattern ELEMENT_SEPARATOR_PATTERN = Pattern.compile("\" ");

    private final PrintStream output;
    private Scanner scanner;
    private RatingApplicationService ratingApplicationService;

    public StandardInputListener(InputStream inputStream, PrintStream outputStream, RatingApplicationService ratingApplicationService) {
        this.scanner = new Scanner(inputStream);
        this.output = outputStream;
        this.ratingApplicationService = ratingApplicationService;
    }

    public void processDataInput() {
        BiConsumer<String[], String> dataInputProcessor = (inputLineEntries, userName) -> {
            double rValue = parseDouble(inputLineEntries[2]);

            String[] location = inputLineEntries[1].split("/");

            String countryName = this.trimQuotes(location[0]);
            String stateName = this.trimQuotes(location[1]);
            String cityName = this.trimQuotes(location[2]);

            ratingApplicationService.processNewUser(userName, rValue, countryName, stateName, cityName);
        };

        this.processInput(dataInputProcessor);
    }

    public void processQueryInput() {
        BiConsumer<String[], String> queryInputProcessor = (inputLineEntries, userName) -> {
            String regionString = inputLineEntries[1];
            String[] regionArray = regionString.split("/");
            String countryName = trimQuotes(regionArray[0]);
            String stateName = getStateName(regionArray);
            String cityName = getCityName(regionArray);

            int rating = StandardInputListener.this.ratingApplicationService.getRating(userName, countryName, stateName, cityName);

            StringBuilder queryOutput = new StringBuilder("\"").append(userName).append("\" ").append(regionString).append(' ').append(rating);
            output.println(queryOutput);
        };

        this.processInput(queryInputProcessor);
    }

    private void processInput(BiConsumer<String[], String> inputLineProcessOperation) {
        String line = scanner.nextLine().trim();

        while (!line.isEmpty()) {

            String[] inputLineEntries = ELEMENT_SEPARATOR_PATTERN.split(line);
            String userName = trimQuotes(inputLineEntries[0]);

            inputLineProcessOperation.accept(inputLineEntries, userName);

            line = scanner.nextLine().trim();
        }
    }

    private String getCityName(String[] regionArray) {
        return regionArray.length >= 3 ? trimQuotes(regionArray[2]) : null;
    }

    private String getStateName(String[] regionArray) {
        return regionArray.length >= 2 ? trimQuotes(regionArray[1]) : null;
    }

    private String trimQuotes(CharSequence s) {
        return QUOTES_PATTERN.matcher(s).replaceAll("");
    }
}
