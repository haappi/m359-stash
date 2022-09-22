package io.github.haappi.arraysProject;

public class Utils {
    private Utils() {
        throw new RuntimeException("This class may not be instantiated.");
    }

    public static String getCountAndPositionOf(int object, int[] array) {
        String outputString = "";
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == object) {
                outputString += object + " found at position " + i + ".";
            }
        }
        return outputString;
    }

}
