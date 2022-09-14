package io.github.haappi.psuedoCode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Just an utils class for common methods that can be shared between the different "programs"<BR>
 * <b>This <i>cannot</i> be instantiated</b><BR>
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

    public static <T> String arrayToString(List<T> array, String seperator) {
        String newString = "";
        for (int i = 0; i < array.size() - 1; i++) {
            newString += array.get(i) + seperator;
        }
        return newString;
    }

    public static <T> String arrayToString(List<T> array) {
        return arrayToString(array, ", ");
    }

    /**
     * Parses a string to the type specified. Pass a variable from that class type.
     *
     * @param input The string to parse
     * @param type  The type to parse to
     * @param <T>   The type to parse to
     * @return The parsed value
     */
    public @Nullable
    static <T> T parseJOptionInput(String input, T type) {
        try {
            if (type instanceof Integer) {
                return (T) Integer.valueOf(input);
            } else if (type instanceof Double) {
                return (T) Double.valueOf(input);
            } else if (type instanceof Float) {
                return (T) Float.valueOf(input);
            } else if (type instanceof Long) {
                return (T) Long.valueOf(input);
            } else if (type instanceof Short) {
                return (T) Short.valueOf(input);
            } else if (type instanceof Byte) {
                return (T) Byte.valueOf(input);
            } else if (type instanceof Boolean) {
                return (T) Boolean.valueOf(input);
            } else if (type instanceof Character) {
                return (T) Character.valueOf(input.charAt(0));
            } else if (type instanceof String) {
                return (T) input;
            } else {
                throw new IllegalArgumentException("Type not supported");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
