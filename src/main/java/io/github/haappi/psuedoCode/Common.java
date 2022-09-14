package io.github.haappi.psuedoCode;

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
}
