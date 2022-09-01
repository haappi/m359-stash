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

    public static String getStockFormatted(Stock stock, Integer count) {
        return stock.toString() + " (x" + count + ")";
    }

    public static void handleChangingPrice(Stock stock, Double priceChange) {
        stock.setPrice(stock.getPrice() + priceChange);
    }

    public static String getStockPrice(Stock stock, Double oldPrice) {
        String message;
        if (stock.getPrice() > oldPrice) {
            message = "The price of " + stock.getName() + " has risen to $" + stock.getPriceFormatted() + " (+" + round(stock.getPrice() - oldPrice) + ")";
        } else if (stock.getPrice() < oldPrice) {
            message = "The price of " + stock.getName() + " has fallen to $" + stock.getPriceFormatted() + " (-" + round(oldPrice - stock.getPrice()) + ")";
        } else {
            message = "The price of " + stock.getName() + " has stayed at $" + stock.getPriceFormatted() + ".";
        }
        return message;
    }

    public static String getRecentMessage(String current, final String newMessage) {
        current = current.replace("Recent Changes: \n", "");
        if (current.split("\n").length > 5) {
            current = "Recent Changes: \n" + current.substring(current.indexOf("\n") + 1);
        }
        return current + "\n" + newMessage;
    }

}
