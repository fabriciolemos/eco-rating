package com.eco.rating.model;

// TODO change name, because it also have the output
public class QueryInput {

    private String userName;
    private String countryName;
    private String stateName;
    private String cityName;
    private  int rating;

    public QueryInput(String userName, String countryName, String stateName, String cityName) {
        this.userName = userName;
        this.countryName = countryName;
        this.stateName = stateName;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "QueryInput{" +
                "userName='" + userName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", rating=" + rating +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getCityName() {
        return cityName;
    }
}
