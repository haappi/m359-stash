package io.github.haappi.arraysProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Gets a random number between the min and the max supplied. (inclusive)
     *
     * @param min The minimum number to get.
     * @param max The maximum number to get.
     * @return A random number between min and the max supplied.
     */
    @SuppressWarnings("unused")
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public static <E> ArrayList<E> shuffleAnArray(ArrayList<E> arrayList) {
        int originalSize = arrayList.size();
        ArrayList<E> copy = (ArrayList<E>) arrayList.clone();
        for (int i = 0; i < originalSize; i++) {
            int indexOne = getRandomNumber(0, originalSize - 1);
            int indexTwo = getRandomNumber(0, originalSize - 1);
            E elementOne = copy.get(indexOne);
            E elementTwo = copy.get(indexTwo);
            copy.set(indexOne, elementTwo);
            copy.set(indexTwo, elementOne);
        }
        return copy;
    }

    public static <E> String printNicely(List<E> list) {
        String output = "";
        for (E elem : list) {
            output += elem + ", ";
        }
        return output;
    }

}
