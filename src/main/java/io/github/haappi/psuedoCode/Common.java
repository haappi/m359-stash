package io.github.haappi.psuedoCode;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

/**
 * Just an utils class for common methods that can be shared between the different "programs"<br>
 * <b>This <i>cannot</i> be instantiated</b><br>
 *
 * @author haappi
 */
public class Common {

    private static final HashMap<Integer, Long> listOfFactorials = new HashMap<>();

    private Common() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
    // an hashmap is used to store the factorials, so that they can be reused if needed (saving time)
    // a hashmap is just a dictionary, everythings assigned to a key, and you can get the value by using the key (think of an actual dictionary)

    /**
     * Calculates the greatest common factor of two numbers.
     *
     * @param a An {@link Integer} to find the GCF of
     * @param b An {@link Integer} to find the GCF of
     * @return An {@link Integer} with the GCF
     */
    @SuppressWarnings("unused")
    public static int GCF(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCF(b, a % b); // recursion until b is 0
    }

    /**
     * Calculates the Least Common Multiple of two {@link Integer}s supplied.
     *
     * @param a The first {@link Integer}
     * @param b The second {@link Integer}
     * @return The LCM of the two {@link Integer}s
     */
    @SuppressWarnings("unused")
    public static int LCM(int a, int b) {
        return a * (b / GCF(a, b)); // multiply a by b divided by the GCF
    }

    @SuppressWarnings("unused")
    public static double round(double number, int places) {
        places = (int) Math.pow(10, places);
        return Math.floor(number * places) / places;
    }

    /**
     * Rounds a {@link Double} to <span color="aqua">2</span> decimal places
     *
     * @param number The {@link Double} to round
     * @return the rounded {@link Double}
     */
    @SuppressWarnings("unused")
    public static double round(Double number) {
        return round(number, 2);
    }

    /**
     * Gets a random number between one and the number supplied (inclusive)
     *
     * @param max The maximum number to get
     * @return A random number between 1 and the number supplied
     */
    @SuppressWarnings("unused")
    public static int getRandomNumber(int max) {
        int min = 0;
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    /**
     * Gets a random number between one and 6 (inclusive)
     *
     * @return A random number between 1 and 6 supplied
     */
    @SuppressWarnings("unused")
    public static int getRandomNumber() {
        return getRandomNumber(6);
    }

    /**
     * Separates each element in a {@link String} with the given {@link String} separator<br>
     *
     * @param string    the {@link String} to seperate
     * @param separator the {@link String} to seperate with
     * @return the seperated {@link String}
     */
    @SuppressWarnings("unused")
    public static String formatStringWithSeparator(String string, String separator) {
        String newString = "";
        for (int i = 0; i < string.length() - 1; i++) {
            newString += string.charAt(i) + separator;
        }
        return newString;
    }

    /**
     * Formats a {@link String} with the given separator & what to split the {@link String} by.
     *
     * @param string    the {@link String} to format
     * @param separator the separator to use
     * @param split     the {@link String} to split the {@link String} by
     * @return the formatted {@link String}
     */
    @SuppressWarnings("unused")
    public static String formatStringWithSeparator(String string, String separator, String split) {
        List<String> splitString = List.of(string.split(split));
        return arrayToString(splitString, separator);
    }

    /**
     * Converts a {@link List} to a {@link String} with the given {@link String} separator
     *
     * @param array     the {@link List} to convert
     * @param separator the {@link String} to seperate with
     * @return the converted {@link String}
     */
    @SuppressWarnings("unused")
    public static <E> String arrayToString(List<E> array, String separator) {
        String newString = "";
        for (E element : array) {
            newString += element + separator; // This is a "for each" loop. It iterates through each element in the array, without having me to specify the index
        }

        newString = newString.substring(0, newString.length() - separator.length()) + ".";
        return newString;
    }

    /**
     * Converts a Generic {@link List} to a {@link String} array
     *
     * @param array the {@link List} to convert
     * @return the converted {@link String} array
     */
    @SuppressWarnings("unused")
    public static <T> String arrayToString(List<T> array) {
        return arrayToString(array, ", ");
    }

    /**
     * Parses a {@link String} to the type specified. Pass a variable from that {@link
     * Class} type. <br>
     * May return {@link null} if the type couldn't be cast to the specified type
     *
     * @param input The {@link String} to parse
     * @param clazz The {@link Class} type to parse to
     * @param <T>   The type to parse to
     * @return The parsed value, cast to the specified {@link Class}
     */
    @SuppressWarnings({"unused", "unchecked"})
    public @Nullable
    static <T> T parseJOptionInput(String input, Class<T> clazz) {
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
     * Returns a {@link Boolean} whether the input is a prime number.
     *
     * @param number An {@link Integer} to check
     * @return Whether the number is prime or not
     */
    @SuppressWarnings("unused")
    public static Boolean isPrime(Integer number) {
        if (number <= 1) { // 1 & I'm starting at 2, so I need to check this case.
            return false;
        }
        if (number > 5) {
            number /= 2;
        }
        for (int i = 2; i < number; i++) { // only need to check up to half the number
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether two {@link Integer}s can be divided without a remainder<br>
     *
     * @param number  the number to divide
     * @param divisor the number to divide by
     * @return whether the two numbers can be divided without a remainder
     */
    @SuppressWarnings("unused")
    public static boolean canBeDividedCleanly(int number, int divisor) {
        return number % divisor == 0;
    }

    /**
     * Checks if the inputted number is an Armstrong number
     * An Armstrong number is a number that is equal to the sum of number to power of length of its digits
     * <i>This is also known also as a Narcissistic number</i>
     *
     * @param number the number to check
     * @return true if the number is an Armstrong number, false otherwise
     */
    @SuppressWarnings("unused")
    public static boolean isArmstrongNumber(int number) {
        int length = String.valueOf(number).length();
        int addedUp = 0;

        for (int i = 0; i < length; i++) {
            addedUp += Math.pow(Integer.parseInt(String.valueOf(String.valueOf(number).charAt(i))), length); // raise the number to the power of the length of the number
        }
        return addedUp == number;
    }

    /**
     * Checks if the inputted number is considered a Strong number
     * A Strong number is a number that is equal to the sum of factorials of its digits
     *
     * @param number the number to check
     * @return true if the number is a Strong number, false otherwise
     */
    @SuppressWarnings("unused")
    public static boolean isStrongNumber(int number) {
        int length = String.valueOf(number).length();
        Long addedUp = 0L;

        for (int i = 0; i < length; i++) {
            addedUp += factorial(Integer.parseInt(String.valueOf(String.valueOf(number).charAt(i))));
        }
        return addedUp == (long) number;
    }

    /**
     * Gets all the factors of the inputted number
     *
     * @param number the number to get the factors of
     * @return a {@link String} of all the factors of the inputted number
     */
    @SuppressWarnings("unused")
    public static String getFactorPairsFor(int number) {
        int x = 1;
        int y = number;

        String factorPairs = "";

        do {
            if (canBeDividedCleanly(number, x)) {
                factorPairs += String.format("%d,%d : ", x, y);
            }
            x++;
            y--;
        } while (x <= y);

        return factorPairs.substring(0, factorPairs.length() - 2);
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
        if (Common.listOfFactorials.containsKey(number)) {
            return Common.listOfFactorials.get(number);
        }
        long output = 1L;
        for (int i = 1; i <= number; i++) {
            output *= i;
        }
        Common.listOfFactorials.put(number, output);
        return output;
    }

    /**
     * Prime factorization of a number
     *
     * @param number the number to factorize
     * @return a {@link String} of the prime factors of the inputted number
     */
    @SuppressWarnings("unused")
    public static String primeFactorization(int number) {
        String output = "";
        int i = 2;
        while (number > 1) {
            if (canBeDividedCleanly(number, i)) {
                output += i + ", ";
                number /= i; // number = number / i
            } else {
                i++;
            }
        }
        return output;
    }

    /**
     * Calculates PI using Leibniz's formula.
     * Rounded to <span color="aqua">2</span> decimal places.
     *
     * @return PI
     */
    @SuppressWarnings("unused")
    public static double calculatePi(int places) {
        int denominator = 1;
        int numerator = 1;
        double pi = 0;
        for (int i = 0; i < 100000; i++) {
            pi += (double) numerator / denominator; // add the fraction
            denominator += 2; // add two to the denominator as per the formula
            numerator *= -1; // flip the sign
        }
        return round(pi * 4, places); // multiply by 4 to get pi (Leibniz formula)
    }

    /**
     * Calculates PI using Leibniz's formula
     *
     * @return PI
     */
    @SuppressWarnings("unused")
    public static double calculatePi() {
        return calculatePi(2);
    }

    /**
     * Simplifies a Fraction.
     *
     * @param numerator   The numerator of the fraction
     * @param denominator The denominator of the fraction
     * @return A {@link String} with the simplified fraction
     */
    @SuppressWarnings("unused")
    public static String reduceFraction(int numerator, int denominator) {
        int gcd = GCF(numerator, denominator);
        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    @SuppressWarnings("unused")
    public static String reduceFraction(String fraction) {
        String[] split = fraction.split("/");
        return reduceFraction(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    /**
     * Simulates a dice roll for you, and returns the <span color="aqua">max, min, & average</span>.<br>
     * <p>
     * All parameters can be @{@link Nullable}
     *
     * @param numberOfDice  The number of dice to roll
     * @param numberOfSides The number of sides on the dice
     * @param times         The number of times to roll the dice
     * @return A {@link String} with the results of the dice roll
     */
    public static String rollADice(@Nullable Integer numberOfDice, @Nullable Integer numberOfSides, @Nullable Integer times) {
        if (numberOfDice == null) {
            numberOfDice = 1;
        }
        if (numberOfSides == null) {
            numberOfSides = 6;
        }
        if (times == null) {
            times = 1;
        }

        int max = 0;
        int min = numberOfSides;
        int average = 0;

        for (int i = 0; i < times; i++) {
            for (int j = 0; j < numberOfDice; j++) {
                int roll = getRandomNumber(numberOfSides);
                if (roll > max) {
                    max = roll;
                }
                if (roll < min) {
                    min = roll;
                }
                average += roll;
            }
        }
        average /= (times * numberOfDice);

        return String.format("Max: %d, Min: %d, Average: %d", max, min, average);
    }

}
