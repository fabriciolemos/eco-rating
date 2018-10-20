package com.eco.rating;

import com.eco.rating.adapter.StandardInputListener;

public class Rating {

    public static void main(String[] args) {

        StandardInputListener standardInputListener = new StandardInputListener();
        standardInputListener.processDataInput();
        standardInputListener.processQueryInput();
    }
}
