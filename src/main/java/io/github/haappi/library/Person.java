package io.github.haappi.library;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Person {
    private final Long libraryCardNumber;
    private String name;
    private Integer age;
    private Integer booksCheckedOut = 0;
    private Book book1 = null;
    private Book book2 = null;
    private Book book3 = null;
    private Double fineDue = 0.00;
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

    public Double getFineDue() {
        return fineDue;
    }

    public void setFineDue(Double fineDue) {
        this.fineDue = fineDue;
        if (fineDue == 0) {
            canCheckout = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String checkoutBook(Book book) {
        if (booksCheckedOut >= bookCheckoutLimit) {
            canCheckout = false;
            return "Walp, it appears you checked out more books your little brain can handle. I can't allow you to checkout books at this time.";
        }
        if (fineDue > 0.00) {
            canCheckout = false;
            return "Walp, it appears you have a fine to pay of: " + fineDue + ". I can't allow you to checkout books at this time.";
        }
        if (book1 == null) {
            book1 = book;
            booksCheckedOut++;
        } else if (book2 == null) {
            book2 = book;
            booksCheckedOut++;
        } else if (book3 == null) {
            book3 = book;
            booksCheckedOut++;
        } else {
            return "You checked out too many books!";
        }
        book.checkoutBook(this);
        return "You checked out " + book.getName() + " by " + book.getAuthor() + "!";
    }

    public void returnBook(Book book) {
        if (book.getWhenBookIsDue() >= System.currentTimeMillis()) {
            this.fineDue += 0.50;
        }
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

    public List<String> getPersonInfo() { // I got too lazy to refactor this into a String
        List<String> returnType = new ArrayList<>();
        returnType.add("Name: " + name); // 0
        returnType.add("Age: " + age); // 1
        returnType.add("Library Card Number: " + libraryCardNumber); // 2
        returnType.add("Books Checked Out: " + booksCheckedOut); // 3
        returnType.add("Book Checkout Limit: " + bookCheckoutLimit); // 4
        returnType.add("Genre Preference: " + genrePreference); // 5
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
