package io.github.haappi.library;

import java.util.ArrayList;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Person {
    private final String name;
    private final Integer age;
    private final Long libraryCardNumber;
    private final ArrayList<Book> booksCheckedOut = new ArrayList<>();
    private Integer bookCheckoutLimit;
    private String genrePreference;

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

    public Long getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public Integer getBookCheckoutLimit() {
        return bookCheckoutLimit;
    }

    public void setBookCheckoutLimit(Integer bookCheckoutLimit) {
        this.bookCheckoutLimit = bookCheckoutLimit;
    }

    public ArrayList<Book> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public String getGenrePreference() {
        return genrePreference;
    }

    public void setGenrePreference(String genrePreference) {
        this.genrePreference = genrePreference;
    }

    public static class PersonBuilder {
        private final String name;
        private final Long libraryCardNumber;
        private final ArrayList<Book> booksCheckedOut = new ArrayList<>();
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
