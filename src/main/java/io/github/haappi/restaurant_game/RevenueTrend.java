package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

public class RevenueTrend { // https://docs.oracle.com/javafx/2/charts/line-chart.htm
    // stores money made / spent in a particular day
    // for us e with a grid thingy (check extras)

    @Expose private final double moneyMade;
    @Expose private final double moneySpent;
    @Expose private final String date;

    public RevenueTrend(double moneyMade, double moneySpent, String date) {
        this.moneyMade = moneyMade;
        this.moneySpent = moneySpent;
        this.date = date;
    }

    public double getMoneyMade() {
        return moneyMade;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s, +%s, -%s.", date, moneyMade, moneySpent);
    }
}