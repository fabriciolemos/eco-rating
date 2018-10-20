package com.eco.rating.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Region {
    protected List<Double> rValues = new ArrayList<>();
    private boolean rValuesSorted;

    public void addRValue(double rValue) {
        this.rValues.add(rValue);
        this.rValuesSorted = false;
    }

    public int getUserRating(User user) {
        if (!rValuesSorted) {
            Collections.sort(rValues);
            rValuesSorted = true;
        }
        int index = Collections.binarySearch(rValues, user.getRValue());
        for (int i = index + 1; i < rValues.size(); i++) {
            if (rValues.get(i) == user.getRValue()) {
                index = i;
            }
        }
        return (index + 1) * 10 / rValues.size();
    }

    @Override
    public String toString() {
        return "Region{" +
                   "rValues=" + rValues +
                   ", rValuesSorted=" + rValuesSorted +
                   '}';
    }
}
