package io.github.haappi.stock;

import java.util.Timer;
import java.util.TimerTask;

import static io.github.haappi.stock.Utils.*;

public class Stock {
    private final String name;
    private Double price;

    public Stock(String name, Double price) {
        this.name = name;
        this.price = round(price);
    }

    public Stock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        return String.format("$%.2f", getPrice());
    }

    public Double setPrice(Double price) {
        this.price = round(price);
        if (this.price < 0.00) {
            setPrice(0.00);
        }
        if (this.price > 60.00) {
            setPrice(60.00);
        }
        return this.getPrice();
    }

    public String toString() {
        return String.format(name + ": %.2f", price);
    }
}
