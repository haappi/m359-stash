package io.github.haappi.stock;

public class Person {
  private final String name;
  private Stock stockOne;
  private Integer stockOneAmount = 0;

  private Stock stockTwo;
  private Integer stockTwoAmount = 0;

  public Double getWallet() {
    return wallet;
  }

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


  public String changeStockOne(Integer amount) {
    if (amount < 0 && stockOneAmount < 0) {
      return "You don't have any shares of " + stockOne.getName() + "!";
    }

    stockOneAmount += amount;
    String message;
    if (amount < 0) {
      wallet += stockOne.getPrice();
      message = "You sold 1 share of " + stockOne.getName() + " for $" + stockOne.getPrice();
    } else {
      if (wallet < stockOne.getPrice()) {
        stockOneAmount -= amount;
        message = "You don't have enough money to buy a share of " + stockOne.getName() + "!";
      } else {
        wallet -= stockOne.getPrice();
        message = "You bought 1 share of " + stockOne.getName() + " for $" + stockOne.getPrice();
      }
    }

    return message;
  }

public String changeStockTwo(Integer amount) {
  if (amount < 0 && stockTwoAmount < 0) {
    return "You don't have any shares of " + stockTwo.getName() + "!";
  }

  stockTwoAmount += amount;
  String message;
  if (amount < 0) {
    wallet += stockTwo.getPrice();
    message = "You sold 1 share of " + stockTwo.getName() + " for $" + stockTwo.getPrice();
  } else {
    if (wallet < stockTwo.getPrice()) {
      stockTwoAmount -= amount;
      message = "You don't have enough money to buy a share of " + stockTwo.getName() + "!";
    } else {
      wallet -= stockTwo.getPrice();
      message = "You bought 1 share of " + stockTwo.getName() + " for $" + stockTwo.getPrice();
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
    Double worth = wallet;
    if (stockOne != null) {
      worth += stockOne.getPrice() * stockOneAmount;
    }
    if (stockTwo != null) {
      worth += stockTwo.getPrice() * stockTwoAmount;
    }
    return worth;
  }

  public void setWallet(Double wallet) {
    this.wallet = wallet;
  }

  public String toString() {
    return name + " " + stockOne + " " + stockTwo + " " + wallet;
  }
}
