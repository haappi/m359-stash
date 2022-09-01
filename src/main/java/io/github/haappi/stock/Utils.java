package io.github.haappi.stock;

public class Utils {
  public Utils() {
    throw new RuntimeException("Utils class should not be instantiated");
  }

  public static Double getRandomPrice() {
    return Math.random() * 40;
  }
}
