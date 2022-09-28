package io.github.haappi.arraysProject;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.*;

@SuppressWarnings({"unused"})
public class Utils {
    private static final HashMap<Integer, Long> listOfFactorials = new HashMap<>();

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
        return (int) (Math.random() * ((max - min) + min + 1)) + min;
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
            int indexOne = getRandomNumber(0, originalSize - 1);
            int indexTwo = getRandomNumber(0, originalSize - 1);
            E elementOne = copy.get(indexOne);
            E elementTwo = copy.get(indexTwo);
            copy.set(indexOne, elementTwo);
            copy.set(indexTwo, elementOne); // This can be condensed into one line. ArrayList.set() returns the element that was replaced.
        }
        return copy;
    }

    /**
     * Flips an array.
     *
     * @param arrayList The {@link List} to flip.
     * @return The flipped array.
     */
    public static <E> ArrayList<E> flipAnArray(ArrayList<E> arrayList) {
        ArrayList<E> copy = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            copy.add(arrayList.get(arrayList.size() - 1 - i));
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
        return "Original array: " + beautifyArray(array) + "\nMin: " + min + "\nMax: " + max + "\nAverage: " + avg;
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
        // the intelij suggestion scares me.
        if (copy.length - (position + 1) >= 0)
            System.arraycopy(array, position + 1 - 1, copy, position + 1, copy.length - (position + 1));
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
            rolls.add(getRandomNumber(numberOfDice, numberOfDice * 6));
        }
        HashSet<String> output = new HashSet<>();
        for (Integer roll : rolls) {
            output.add("Count of " + roll + ": " + countOf(rolls, roll));
        }
        ArrayList<String> outputList = new ArrayList<>(output);
        outputList.sort(Comparator.comparingInt(o -> Integer.parseInt(o.split(" ")[2].split(":")[0])));
        // https://stackoverflow.com/a/54342426
        return beautifyArray(outputList, "\n");
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

    /**
     * Translates a given {@link String} by a few characters with the provided offset {@link Integer}. <br>
     *
     * @param string The {@link String} to translate.
     * @param offset The offset to translate the {@link String} by.
     * @return The translated {@link String}.
     */
    public static String translate(String string, int offset) {
        String output = "";
        for (char c : string.toCharArray()) {
            char newChar = (char) (c + offset);
            if (newChar > 126) {
                newChar = (char) (newChar - 95);
            } else if (newChar < 32) {
                newChar = (char) (newChar + 95);
            }
            output += newChar;
        }
        return string + " -> " + output;
    }

    /**
     * Parses a {@link String} to the type specified. Pass a variable from that {@link Class} type.
     * <br>
     * May return {@link null} if the type couldn't be cast to the specified type
     *
     * @param input The {@link String} to parse
     * @param clazz The {@link Class} type to parse to
     * @param <T>   The type to parse to
     * @return The parsed value, cast to the specified {@link Class}
     */
    @SuppressWarnings({"unused", "unchecked"})
    public @Nullable
    static <T> T parseInput(String input, Class<T> clazz) {
        try {
            if (input == null) {
                return null;
            }
            if (clazz == Integer.class) {
                return (T) Integer.valueOf(input);
            } else if (clazz == Double.class) {
                return (T) Double.valueOf(input);
            } else if (clazz == Float.class) {
                return (T) Float.valueOf(input);
            } else if (clazz == Long.class) {
                return (T) Long.valueOf(input);
            } else if (clazz == Short.class) {
                return (T) Short.valueOf(input);
            } else if (clazz == Byte.class) {
                return (T) Byte.valueOf(input);
            } else if (clazz == Boolean.class) {
                return (T) Boolean.valueOf(input.toLowerCase());
            } else if (clazz == String.class) {
                return (T) input;
            } else {
                throw new IllegalArgumentException("Unknown type: " + clazz);
            }
        } catch (NumberFormatException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses a {@link String} to a String. This just exists for filler purposes.
     * <br>
     * May return {@link null} if the type couldn't be cast to the specified type
     *
     * @param input The {@link String} to parse
     * @return The parsed value, cast to the specified {@link Class}
     */
    @SuppressWarnings({"unused"})
    public @Nullable
    static String parseInput(String input) {
        return parseInput(input, String.class);
    }

    public static int[][] generate2DIntArray() {
        // 0 = empty
        // 1 = filled
        int[][] array = new int[10][10];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i != 0) {
                    // check the one under to see if its filled or not
                    if (array[i - 1][j] == 0) {
                        array[i][j] = getRandomNumber(0, 5) > 1 ? 0 : 1;
                    } else {
                        array[i][j] = 1;
                    }
                } else {
                    array[i][j] = getRandomNumber(0, 5) > 1 ? 0 : 1;
                }
            }
        }
        return array;
    }

    /**
     * Returns a {@link String} formatted with separators for each element in the provided {@link List}.
     *
     * @param array The {@link List} to format.
     * @return The formatted {@link String}.
     */
    public static <E> String beautifyArray(List<E> array) {
        return beautifyArray(array, ", ");
    }

    /**
     * Returns a {@link String} formatted with separators for each element in the provided {@link List}.
     *
     * @param array     The {@link List} to format.
     * @param separator The separator to use.
     * @return The formatted {@link String}.
     */
    public static <E> String beautifyArray(List<E> array, String separator) {
        String output = "";
        for (E e : array) {
            output += e + separator;
        }
        return output.substring(0, output.length() - separator.length());
    }

    /**
     * Makes a primitive int array looks nice
     *
     * @param array The array to beautify
     * @return The beautified array
     */
    public static String beautifyArray(int[] array) {
        ArrayList<Integer> integerArray = new ArrayList<>();
        for (int i : array) {
            integerArray.add(i);
        }
        return beautifyArray(integerArray, ", ");
    }

    /**
     * Gets the {@link String} representation from a {@link KeyEvent}.
     * <br>
     *
     * @param event The {@link KeyEvent} to get the {@link String} representation from.
     * @return The {@link String} representation of the {@link KeyEvent}.
     */
    public static String getStringFrom(KeyEvent event) {
        return ((TextField) event.getSource()).getText();
    }

    /**
     * Gets the count of the provided Object in the provided {@link List}.
     *
     * @param list   The {@link List} to search in.
     * @param object The Object to search for.
     * @return The count of the provided Object in the provided {@link List}.
     */
    public static <E> int countOf(List<E> list, Object object) {
        int output = 0;
        for (Object e : list) {
            if (e.equals(object))
                output++;
        }
        return output;
    }

    /**
     * Gets the factorial of the given {@link Integer}
     *
     * @param number An {@link Integer} to find the factorial of.
     * @return An {@link Long}, returning the factorial.
     */
    @SuppressWarnings("unused")
    public static @Nullable Long factorial(Integer number) {
        if (number < 0) {
            return null;
        }
        if (Utils.listOfFactorials.containsKey(number)) {
            return Utils.listOfFactorials.get(number);
        }
        long output = 1L;
        for (int i = 1; i <= number; i++) {
            output *= i;
        }
        Utils.listOfFactorials.put(number, output);
        return output;
    }

    /**
     * Somehow get a row of Pascal's Triangle
     *
     * @param row The row to get
     * @return The row's numbers.
     */
    public static String getPascalsTriangleRow(int row) {
        String output = "";
        for (int i = 0; i <= row; i++) {
            output += Utils.factorial(row) / (Utils.factorial(i) * Utils.factorial(row - i)) + " ";
            // https://ptri1.tripod.com/#:~:text=A%20number%20in%20the%20triangle,-------
            // the formula is n! / r! * (n - r)!
            // you still factorial the numbers inside
            // where n is the # row and r is the element in the row
        }
        return output;
    }

    /**
     * Constructs a Pascal Triangle until the row specified
     *
     * @param row The row to stop at
     * @return The Pascal Triangle
     */
    public static String getPascalsTriangle(int row) {
        String output = "";
        for (int i = 0; i <= row; i++) {
            output += getPascalsTriangleRowSpacing(row - i) + getPascalsTriangleRow(i) + getPascalsTriangleRowSpacing(row - i) + "\n";
            // this has to be row - i because the top iof the triangle needs the most amoubnt of spaces
        }
        return output;
    }

    /**
     * Gets spacing for the Pascal Triangle row
     *
     * @param row The row to get spacing for
     * @return The spacing
     */
    public static String getPascalsTriangleRowSpacing(int row) {
        return " ".repeat(Math.max(0, row));
    }


// todo pascals triangle
}
