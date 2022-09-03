package io.github.haappi.library;

public class Utils {
    public Utils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Long getRandomStringOfNumbers(Integer length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(getRandomNumber(1, 10));
        }
        return Long.parseLong(sb.toString());
    }

    public static Integer getRandomNumber(Integer min, Integer max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
