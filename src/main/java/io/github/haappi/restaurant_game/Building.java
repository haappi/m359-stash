package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Building {
    @Expose
    private final ArrayList<RevenueTrend> trends = new ArrayList<>();
    @Expose
    private final ArrayList<Staff> staff = new ArrayList<>();
    @Expose
    private double rating = 5.00;
    @Expose
    private int currentLevel = 1;
    @Expose
    private double money = 1000;
    @Expose
    private int customersADay;

    @Expose
    private RestaurantView restaurantView;

    public int getUpgradeCost() {
        return currentLevel * 1000;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public ArrayList<Staff> addStaff(Staff staff) {
        this.staff.add(staff);
        return this.staff;
    }

    public void upgrade() {
        currentLevel++;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void addTrend(RevenueTrend trend) {
        trends.add(trend);
    }

    public ArrayList<RevenueTrend> getTrends() {
        return trends;
    }


    public void setRestaurantView(RestaurantView restaurantView) {
        if (this.restaurantView != null) {
            throw new IllegalStateException("RestaurantView already set");
        }
        this.restaurantView = restaurantView;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getMoney() {
        return money;
    }

    public void addOrRemoveMoney(double money) {
        this.money += money;
    }
}
