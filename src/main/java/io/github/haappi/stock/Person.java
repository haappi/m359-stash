package io.github.haappi.stock;

public class Person {
    private final String name;
    private Stock stockOne;
    private Integer stockOneAmount;

    private Stock stockTwo;
    private Integer stockTwoAmount;
    private Double wallet = 0.00;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Double wallet) {
        this.name = name;
        this.wallet = wallet;
    }

    public Integer getStockOneAmount() {
        return stockOneAmount;
    }

    public Integer getStockTwoAmount() {
        return stockTwoAmount;
    }

    public Integer changeStockOneAmount(Integer amount) {
        return stockOneAmount += amount;
    }

    public Integer changeStockTwoAmount(Integer amount) {
        return stockTwoAmount += amount;
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
        return stockOne.getPrice() + stockTwo.getPrice() + wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public String toString() {
        return name + " " + stockOne + " " + stockTwo + " " + wallet;
    }
}
