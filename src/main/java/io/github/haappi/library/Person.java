package io.github.haappi.library;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Person {
    private final String name;
    private final Integer age;
    private final Long libraryCardNumber;
    private final ArrayList<Book> booksCheckedOut = new ArrayList<>();
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

    public ArrayList<Book> getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public ArrayList<Book> checkoutBook(Book book) {
        if (booksCheckedOut.size() < bookCheckoutLimit) {
            booksCheckedOut.add(book);
            book.setCheckedOutBy(this);
        }
        return booksCheckedOut;
    }

    public ArrayList<Book> returnBook(Book book) {
        booksCheckedOut.remove(book);
        book.setCheckedOutBy(null);
        return booksCheckedOut;
    }

    public Integer getNumberOfBooksCheckedOut() {
        return booksCheckedOut.size();
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
        returnType.add("Books Checked Out: " + booksCheckedOut.size());
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
