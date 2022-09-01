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

    public static void handleChangingPrice(Stock stock, Double priceChange) {
        stock.setPrice(stock.getPrice() + priceChange);
    }

}
