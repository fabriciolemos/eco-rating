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

    public int getRValueRating(double rValue) {
        int index = getRValueIndex(rValue);
        double percentageOfEntriesWithLowerOrEqualRValue = (index + 1.0) / rValues.size();
        return getRating(percentageOfEntriesWithLowerOrEqualRValue);
    }

    private int getRating(double percentage) {
        return (int) Math.ceil(percentage * 10);
    }

    private int getRValueIndex(double rValue) {
        sortRValues();
        int index = Collections.binarySearch(rValues, rValue);
        return advanceIndexToMaxWithSameValue(index);
    }

    private int advanceIndexToMaxWithSameValue(int index) {
        for (int i = index + 1; i < rValues.size(); i++) {
            if (rValues.get(i).equals(rValues.get(index))) {
                index = i;
            }
        }
        return index;
    }

    private void sortRValues() {
        if (!rValuesSorted) {
            Collections.sort(rValues);
            rValuesSorted = true;
        }
    }

    @Override
    public String toString() {
        return "Region{" +
                   "rValues=" + rValues +
                   ", rValuesSorted=" + rValuesSorted +
                   '}';
    }
}
