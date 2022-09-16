package io.github.haappi.psuedoCode;

import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * Just an utils class for common methods that can be shared between the different "programs"<br>
 * <b>This <i>cannot</i> be instantiated</b><br>
 *
 * @author haappi
 */
public class Common {

  private Common() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  public static String formatStringWithSeparator(String string, String separator) {
    String newString = "";
    for (int i = 0; i < string.length() - 1; i++) {
      newString += string.charAt(i) + separator;
    }
    return newString;
  }

  public static double heronsMethodForSquare(double guess) {
    return (guess + (42 / guess)) / 2;
  }

  public static String formatStringWithSeparator(String string, String separator, String split) {
    List<String> splitString = List.of(string.split(split));
    return arrayToString(splitString, separator);
  }

  public static <E> String arrayToString(List<E> array, String separator) {
    String newString = "";
    for (int i = 0; i < array.size() - 1; i++) {
      newString += array.get(i) + separator;
    }

    newString = newString.substring(0, newString.length() - separator.length()) + ".";
    return newString;
  }

  public static <T> String arrayToString(List<T> array) {
    return arrayToString(array, ", ");
  }

  /**
   * Parses a {@link java.lang.String} to the type specified. Pass a variable from that {@link
   * java.lang.Class} type. <br>
   * May return {@link null} if the type couldn't be cast to the specified type
   *
   * @param input The {@link java.lang.String} to parse
   * @param clazz The {@link java.lang.Class} type to parse to
   * @param <T> The type to parse to
   * @return The parsed value, cast to the specified {@link java.lang.Class}
   */
  @SuppressWarnings("unchecked")
  public @Nullable static <T> T parseJOptionInput(String input, Class<T> clazz) {
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
   * Returns a boolean whether the input is a prime number.
   *
   * @param number An int to check
   * @return Whether the number is prime or not
   */
  public static boolean isPrime(int number) {
    if (number <= 1) { // 1 & I'm starting at 2, so I need to check this case.
      return false;
    }
    for (int i = 2; i < number / 2; i++) { // only need to check up to half the number
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the factorial of the given {@link java.lang.Integer}
   *
   * @param number An {@link java.lang.Integer} to find the factorial of.
   * @return An {@link java.lang.Long}, returning the factorial.
   */
  public static @Nullable Long factorial(Integer number) {
    if (number < 0) {
      return null;
    }
    long output = 1L;
    int iteration = number;
    while (iteration != 1) {
      output *= iteration;
      iteration--;
    }
    return output;
  }
}
