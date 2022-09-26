package io.github.haappi.arraysProject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    private Utils() {
        throw new RuntimeException("This class may not be instantiated.");
    }

    /**
     * Basically just a stupid version of finding the count & position of an element in an array.
     *
     * @param object The int to find.
     * @param array  The int array to search in.
     * @return A String containing of the count and position of the element in the array.
     */
    public static String getCountAndPositionOf(int object, int[] array) {
        String outputString = "";
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == object) {
                outputString += object + " found at position " + i + ".\n";
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
        return (int) (Math.random() * ((max - min) + min)) + min;
    }

    /**
     * Shuffles an array.
     *
     * @param arrayList The array to shuffle.
     * @return The shuffled array.
     */
    public static <E> ArrayList<E> shuffleAnArray(ArrayList<E> arrayList) {
        int originalSize = arrayList.size();
        ArrayList<E> copy = (ArrayList<E>) arrayList.clone();
        for (int i = 0; i < originalSize; i++) {
            int indexOne = getRandomNumber(0, originalSize);
            int indexTwo = getRandomNumber(0, originalSize);
            E elementOne = copy.get(indexOne);
            E elementTwo = copy.get(indexTwo);
            copy.set(indexOne, elementTwo);
            copy.set(indexTwo, elementOne); // This can be condensed into one line. ArrayList.set() returns the element that was replaced.
        }
        return copy;
    }

    /**
     * Returns a nicely formatted string of the array. A List is used as that's the Superclass of ArrayList.
     *
     * @param list The list to format.
     * @return A nicely formatted string of the array.
     */
    public static <E> String printNicely(List<E> list) {
        String output = "";
        for (E elem : list) {
            output += elem.toString() + ", ";
        }
        return output;
    }

    /**
     * Gets the minimum and maximum value of an array.
     *
     * @param array The array to get the min and max of.
     * @return A string containing the min and max of the array.
     */
    public static String minMaxArray(int[] array) {
        int min = array[0];
        int max = array[0];
        int avg = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
            avg += array[i];
        }
        avg /= array.length;
        return "Min: " + min + ", Max: " + max + ", Avg: " + avg;
    }

    /**
     * Returns the resource path of a given resource.
     *
     * @param s The resource to get the path of.
     * @return The path of the resource.
     */
    public static Path getResourcePath(String s) {
        return Path.of("src", "main", "resources", s);
    }

    /**
     * Adds an element to an array at a given position.
     *
     * @param array    The array to add the element to.
     * @param position The position to add the element at.
     * @param element  The element to add.
     * @return The array with the element added.
     */
    public static int[] addElementToArrayAtAGivenPosition(int[] array, int position, int element) {
        int[] copy = new int[array.length + 1];
        System.arraycopy(array, 0, copy, 0, array.length); // Java provides a method to copy arrays. This is probably just a big for loop.
        copy[position] = element;
        for (int i = position + 1; i < copy.length; i++) {
            copy[i] = array[i - 1]; // the intelij suggestion scares me.
        }
        return copy;
    }

    /**
     * Replaces an element in an array with a given position.
     *
     * @param array    The array to replace the element in.
     * @param position The position of the element to replace.
     * @param element  The element to replace the element at the given position with.
     * @return The array with the element replaced.
     */
    public static int[] replaceElementInArray(int[] array, int position, int element) {
        array[position] = element;
        return array;
    }

    /**
     * Gets all elements starting with the given query.<br>
     * <b>This is Case Insensitive</b>
     *
     * @param list  The {@link List} to get the elements from.
     * @param query The query to search for.
     * @return A {@link List} containing all elements starting with the given query.
     */
    public static <E> List<E> getElementsStartingWith(List<E> list, E query) {
        List<E> output = new ArrayList<>();
        for (E elem : list) {
            if (elem.toString().toLowerCase().startsWith(query.toString().toLowerCase())) {
                output.add(elem);
            }
        }
        return output;
    }

    /**
     * Rolls x dice y times and display how many of each sum occurs
     *
     * @param numberOfDice  The number of dice to roll.
     * @param numberOfTimes The number of times to roll the dice.
     * @return A {@link String} containing the number of each sum.
     */
    // Add up the sums of the x die. Just do that y-times and return that in the end
    public static String rollDiceThing(int numberOfDice, int numberOfTimes) {
        ArrayList<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < numberOfTimes; i++) {
            rolls.add(getRandomNumber(1, numberOfDice));
        }
        String output = "";
        for (int i = 0; i < numberOfDice; i++) {
            int count = 0;
            for (int roll : rolls) {
                if (roll == i + 1) {
                    count++;
                }
            }
            output += "Number of " + (i + 1) + "'s: " + count + "\n";
        }
        return output;
    }

    /**
     * Gets all elements starting with the given query.<br>
     * <b>This is Case Insensitive</b>
     *
     * @param set   The {@link Set} to get the elements from.
     * @param query The query to search for.
     * @return A {@link Set} containing all elements starting with the given query.
     */
    public static <E> Set<E> getElementsStartingWith(Set<E> set, E query) {
        Set<E> output = new HashSet<>();
        for (E elem : set) {
            if (elem.toString().toLowerCase().startsWith(query.toString().toLowerCase())) {
                output.add(elem);
            }
        }
        return output;
    }

    /**
     * Replaces a given element with another in an int array.
     *
     * @param array      The array to replace the element in.
     * @param oldElement The element to replace.
     * @param newElement The element to replace with.
     * @return The updated array.
     */
    public static int[] replaceElementWithAnotherInArray(int[] array, int oldElement, int newElement) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == oldElement) {
                array[i] = newElement;
                break;
            }
        }
        return array;
    }

// todo pascals triangle
}
