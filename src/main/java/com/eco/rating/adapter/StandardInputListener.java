package com.eco.rating.adapter;

import com.eco.rating.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StandardInputListener {

    private final Scanner scanner = new Scanner(System.in);

    public List<User> getDataInput() {

        List<User> inputData = new ArrayList<>();
        String line = scanner.nextLine().trim();

        while (!line.equals("")) {
            String[] strings = line.split("\" ");

            String[] locationInputArray = strings[1].split("/");
            Country country = new Country(locationInputArray[0].substring(1));
            State state = new State(locationInputArray[1], country);
            City city = new City(locationInputArray[2], state);

            inputData.add(new User(strings[0].substring(1), city, Double.parseDouble(strings[2])));

            line = scanner.nextLine().trim();
        }
        return inputData;
    }

    public List<QueryInput> getQueryInput() {

        List<QueryInput> queryData = new ArrayList<>();
        String line = scanner.nextLine().trim();
        System.out.println("line = " + line);
        System.out.println("line = " + line);

        while (!line.equals("")) {
            String[] strings = line.split("\" ");
            String userName = strings[0].substring(1);
            String[] region = strings[1].split("/");
            String countryName = trimQuotes(region[0]);
            String stateName = region.length >= 2 ? trimQuotes(region[1]) : null;
            String cityName = region.length >= 3 ? trimQuotes(region[2]) : null;
            QueryInput queryInput = new QueryInput(userName, countryName, stateName, cityName);

            queryData.add(queryInput);
            line = scanner.nextLine().trim();
        }
//        System.out.println("queryData = " + queryData);
        return queryData;
    }

    private String trimQuotes(String s) {
        return s.replaceAll("^\"|\"$", "");
    }
}
