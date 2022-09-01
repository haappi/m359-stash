package io.github.haappi.stock;

import static io.github.haappi.stock.Utils.round;

public class Person {
    private final String name;
    private Stock stockOne;
    private Integer stockOneAmount = 0;

    private Stock stockTwo;
    private Integer stockTwoAmount = 0;
    private Double wallet = 0.00;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Double wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public Integer getStockOneAmount() {
        return stockOneAmount;
    }

    public Integer getStockTwoAmount() {
        return stockTwoAmount;
    }

    public String changeStockOne(Integer amount) {
        if (amount < 0 && stockOneAmount <= 0) {
            return "You don't have any shares of " + stockOne.getName() + "!";
        }

        stockOneAmount += amount;
        String message;
        if (amount < 0) {
            wallet += stockOne.getPrice();
            message = "You sold 1 share of " + stockOne.getName() + " for " + stockOne.getPriceFormatted();
        } else {
            if (wallet < stockOne.getPrice()) {
                stockOneAmount -= amount;
                message = "You don't have enough money to buy a share of " + stockOne.getName() + "!";
            } else {
                wallet -= stockOne.getPrice();
                message = "You bought 1 share of " + stockOne.getName() + " for " + stockOne.getPriceFormatted();
            }
        }

        return message;
    }

    public String changeStockTwo(Integer amount) {
        if (amount < 0 && stockTwoAmount <= 0) {
            return "You don't have any shares of " + stockTwo.getName() + "!";
        }

        stockTwoAmount += amount;
        String message;
        if (amount < 0) {
            wallet += stockTwo.getPrice();
            message = "You sold 1 share of " + stockTwo.getName() + " for " + stockTwo.getPriceFormatted();
        } else {
            if (wallet < stockTwo.getPrice()) {
                stockTwoAmount -= amount;
                message = "You don't have enough money to buy a share of " + stockTwo.getName() + "!";
            } else {
                wallet -= stockTwo.getPrice();
                message = "You bought 1 share of " + stockTwo.getName() + " for " + stockTwo.getPriceFormatted();
            }
        }

        return message;
    }

    public String getName() {
        return name;
    }

    public Stock getStockOne() {
        return stockOne;
    }

    public void setStockOne(Stock stockOne) {
        this.stockOne = stockOne;
    }

    public Stock getStockTwo() {
        return stockTwo;
    }

    public void setStockTwo(Stock stockTwo) {
        this.stockTwo = stockTwo;
    }

    public Double getNetworth() {
        setWallet(round(this.getWallet()));
        Double worth = this.wallet;
        if (stockOne != null) {
            worth += round(stockOne.getPrice() * stockOneAmount);
        }
        if (stockTwo != null) {
            worth += round(stockTwo.getPrice() * stockTwoAmount);
        }
        return round(worth);
    }

    public String toString() {
        return String.format(name + " " + stockOne + " " + stockTwo + " $.2f", wallet);
    }
}
