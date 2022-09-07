package io.github.haappi.library;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Person {
    private final String name;
    private final Integer age;
    private final Long libraryCardNumber;
    private Integer booksCheckedOut = 0;
    private Book book1 = null;
    private Book book2 = null;
    private Book book3 = null;

    private Integer bookCheckoutLimit;
    private String genrePreference;
    private Boolean canCheckout = true;

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.libraryCardNumber = builder.libraryCardNumber;
        this.bookCheckoutLimit = builder.bookCheckoutLimit;
        this.genrePreference = builder.genrePreference;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getCanCheckout() {
        return canCheckout;
    }

    public void setCanCheckout(Boolean canCheckout) {
        this.canCheckout = canCheckout;
    }

    public Long getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public Integer getBookCheckoutLimit() {
        return bookCheckoutLimit;
    }

    public void setBookCheckoutLimit(Integer bookCheckoutLimit) {
        this.bookCheckoutLimit = bookCheckoutLimit;
    }

    public @Nullable Book getBook1() {
        return book1;
    }

    public @Nullable Book getBook2() {
        return book2;
    }

    public @Nullable Book getBook3() {
        return book3;
    }

    public boolean checkoutBook(Book book) {
        if (booksCheckedOut > bookCheckoutLimit) {
            canCheckout = false;
            return false;
        }
        if (book1 == null) {
            book1 = book;
            booksCheckedOut++;
            return true;
        } else if (book2 == null) {
            book2 = book;
            booksCheckedOut++;
            return true;
        } else if (book3 == null) {
            book3 = book;
            booksCheckedOut++;
            return true;
        }
        return false;
    }

    public void returnBook(Book book) {
        if (book1 == book) {
            book1 = null;
            booksCheckedOut--;
        } else if (book2 == book) {
            book2 = null;
            booksCheckedOut--;
        } else if (book3 == book) {
            book3 = null;
            booksCheckedOut--;
        }
        book.setCheckedOutBy(null);
    }

    public Integer getNumberOfBooksCheckedOut() {
        return booksCheckedOut;
    }

    public String getGenrePreference() {
        return genrePreference;
    }

    public void setGenrePreference(String genrePreference) {
        this.genrePreference = genrePreference;
    }

    public String toString() {
        return name;
    }

    public List<String> getPersonInfo() {
        List<String> returnType = new ArrayList<>();
        returnType.add("Name: " + name);
        returnType.add("Age: " + age);
        returnType.add("Library Card Number: " + libraryCardNumber);
        returnType.add("Books Checked Out: " + booksCheckedOut);
        returnType.add("Book Checkout Limit: " + bookCheckoutLimit);
        returnType.add("Genre Preference: " + genrePreference);
        return returnType;
    }

    public static class PersonBuilder {
        private final String name;
        private final Long libraryCardNumber;
        private Integer age;
        private Integer bookCheckoutLimit = 1;
        private String genrePreference;

        public PersonBuilder(String name) {
            this.name = name;
            this.libraryCardNumber = getRandomStringOfNumbers(10);
        }

        public PersonBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public PersonBuilder bookCheckoutLimit(Integer bookCheckoutLimit) {
            this.bookCheckoutLimit = bookCheckoutLimit;
            return this;
        }

        public PersonBuilder genrePreference(String genrePreference) {
            this.genrePreference = genrePreference;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
