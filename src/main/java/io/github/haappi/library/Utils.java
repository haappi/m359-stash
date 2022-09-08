package io.github.haappi.library;

import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    public static String booleanToString(Boolean bool) {
        return bool ? "Yes" : "No";
    }

    public static Path getResourcePath(String s) {
        return Path.of("src", "main", "resources", s);
    }

    public static <T> T getRandomElement(List<T> array) { // <T> is basically a placeholder for the type of the array
        return array.get(getRandomNumber(0, array.size() - 1));
    }

    public static Integer getRandomElement(Integer sizeOfList) {
        return getRandomNumber(0, sizeOfList - 1);
    }

    public static <T> String listToString(List<T> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (T element : list) {
            sb.append(element).append(separator);
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> stringToList(String string, String separator) {
        List<E> list = new ArrayList<>();
        for (String element : string.split(separator)) {
            list.add((E) element);
        }
        return list;
    }

    public static <E> List<E> stringToList(String string) {
        return stringToList(string, ", ");
    }

    public static <E> String listToString(List<E> list) {
        return listToString(list, ", ");
    }

//    public static List<Book> getBooksToAdd(Person person) {
//        return person.getBooksCheckedOut();
//    }

    public static List<Book> getBooksToAdd(List<Book> books) {
        List<Book> returnBooks = new ArrayList<>(books);

        returnBooks.removeIf(book -> !book.isAvailable());
        return returnBooks;
    }

    public static String getBookInformation(@Nullable Book book) {
        if (book == null) {
            return "";
        }
        return book.getInformation();
    }
}
