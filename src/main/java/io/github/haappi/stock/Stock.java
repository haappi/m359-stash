package io.github.haappi.stock;

public class Stock {
  private final String name;
  private Double price;

  public Stock(String name, Double price) {
    this.name = name;
    this.price = price;
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

  public void setPrice(Double price) {
    this.price = price;
  }

  public String toString() {
    return name + " " + price;
  }
}
