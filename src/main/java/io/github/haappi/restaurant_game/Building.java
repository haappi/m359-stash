package io.github.haappi.restaurant_game;

import java.util.ArrayList;

public class Building {
    private final ArrayList<RevenueTrend> trends = new ArrayList<>();
    private double rating = 5.00;
    private int customersADay;

    private RestaurantView restaurantView;
    private FarmingView farmingView;

    public void setFarmingView(FarmingView farmingView) {
        if (this.farmingView != null) {
            throw new IllegalStateException("FarmingView already set");
        }
        if (this.restaurantView != null) {
            throw new IllegalStateException("Cannot set both FarmingView and RestaurantView");
        }
        this.farmingView = farmingView;
    }

    public void setRestaurantView(RestaurantView restaurantView) {
        if (this.restaurantView != null) {
            throw new IllegalStateException("RestaurantView already set");
        }
        if (this.farmingView != null) {
            throw new IllegalStateException("Cannot set both FarmingView and RestaurantView");
        }
        this.restaurantView = restaurantView;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
