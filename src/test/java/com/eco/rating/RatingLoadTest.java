package com.eco.rating;

import com.eco.rating.adapter.StandardInputListener;
import com.eco.rating.application.RatingApplicationService;
import com.eco.rating.model.CountryRepository;
import com.eco.rating.model.UserRepository;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;

import static java.lang.String.format;

public class RatingLoadTest {

    @Test
    public void loadTestRating() {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(getTestInput().getBytes());

        StandardInputListener inputListener = getInputListener(inputStream);

        processInput(inputListener);
        processQuery(inputListener);
    }

    public StandardInputListener getInputListener(ByteArrayInputStream inputStream) {
        RatingApplicationService ratingApplicationService = new RatingApplicationService(new UserRepository(), new CountryRepository());
        return new StandardInputListener(inputStream, new PrintStream(new ByteArrayOutputStream()), ratingApplicationService);
    }

    public String getTestInput() {
        StringBuilder queryBuilder = new StringBuilder();
        StringBuilder inputBuilder = new StringBuilder();
        int numberOfCountries = 10;
        int numberOfStatesPerCountry = 10;
        int numberOfCitiesPerState = 100;
        int numberOfUsersPerCity = 100;
        for (int countryIndex = 1; countryIndex <= numberOfCountries; countryIndex++) {
            for (int stateIndex = 1; stateIndex <= numberOfStatesPerCountry; stateIndex++) {
                for (int cityIndex = 1; cityIndex <= numberOfCitiesPerState; cityIndex++) {
                    for (int userIndex = 1; userIndex <= numberOfUsersPerCity; userIndex++) {
                        appendToInput(queryBuilder, inputBuilder, countryIndex, stateIndex, cityIndex, userIndex);
                    }
                }

            }
        }
        inputBuilder.append('\n');
        queryBuilder.append('\n');

        return inputBuilder.append(queryBuilder).toString();
    }

    public void processQuery(StandardInputListener inputListener) {
        this.evaluateOperationTime(StandardInputListener::processQueryInput, inputListener, "Process query");
    }

    public void processInput(StandardInputListener inputListener) {
        this.evaluateOperationTime(StandardInputListener::processDataInput, inputListener, "Process input");
    }

    public void appendToInput(StringBuilder queryBuilder, StringBuilder inputBuilder, int countryIndex, int stateIndex, int cityIndex, int userIndex) {
        String format = "\"%s\" \"%s/%s/%s\"";
        String userName = "user-" + userIndex + '-' + countryIndex + '-' + stateIndex + '-' + cityIndex;
        String queryLine = format(format, userName, "country " + countryIndex, "state " + stateIndex, "city " + cityIndex);

        double rvalue = userIndex / 10.0;

        inputBuilder.append(queryLine).append(' ').append(rvalue).append('\n');
        queryBuilder.append(queryLine).append('\n');
    }

    public void evaluateOperationTime(Consumer<StandardInputListener> operation, StandardInputListener inputListener, String operationName) {
        Instant start = Instant.now();
        operation.accept(inputListener);
        Instant end = Instant.now();

        System.out.println(operationName + " operation - time taken: " + Duration.between(start, end).toMillis() + " milliseconds");
    }
}