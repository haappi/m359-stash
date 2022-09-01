package io.github.haappi.stock;

public class Utils {
  public Utils() {
    throw new RuntimeException("Utils class should not be instantiated");
  }

  public static Double getRandomPrice(Double min, Double max) {
    return Math.random() * (max - min) + min;
  }

  public static Integer getRandomInteger(Integer min, Integer max) {
    return (int) (Math.random() * (max - min + 1) + min);
  }

  public static Double round(Double value, Integer places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }

  public static Double round(Double value) {
    return round(value, 2);
  }

  public static void handleChangingPrice(Stock stock, Double priceChange) {
    stock.setPrice(stock.getPrice() + priceChange);
  }
}
