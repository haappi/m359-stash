package io.github.haappi.psuedoCode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

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
   * Parses a string to the type specified. Pass a variable from that class type. <br>
   * May return null if the type couldn't be cast to the specified type
   *
   * @param input The string to parse
   * @param clazz The class type to parse to
   * @param <T> The type to parse to
   * @return The parsed value
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
        return (T) Boolean.valueOf(input);
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
}
