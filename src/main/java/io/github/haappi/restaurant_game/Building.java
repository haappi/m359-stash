package io.github.haappi.restaurant_game;

import java.util.ArrayList;

public class Building {
    private final ArrayList<RevenueTrend> trends = new ArrayList<>();
    private double rating = 5.00;
    private int customersADay;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
