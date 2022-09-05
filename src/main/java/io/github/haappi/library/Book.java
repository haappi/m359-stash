package io.github.haappi.library;

import org.jetbrains.annotations.Nullable;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Book {
    private final String name;
    private final String author;
    private final String genre;
    private final Long bookId;
    private final Integer minimumAge = 0;
    private final Integer checkoutDuration = 14;
    private Integer checkoutTimeRemaining = -1;
    private Person checkedOutBy; // I'm too lazy to loop through the People array

    public @Nullable Person getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(Person checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
        this.checkoutTimeRemaining = checkoutDuration;
    }

    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.bookId = getRandomStringOfNumbers(8);
    }

    public Integer getCheckoutTimeRemaining() {
        return checkoutTimeRemaining;
    }

    public void setCheckoutTimeRemaining(Integer checkoutTimeRemaining) {
        this.checkoutTimeRemaining = checkoutTimeRemaining;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Long getBookId() {
        return bookId;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public Integer getCheckoutDuration() {
        return checkoutDuration;
    }

    public boolean isAvailable() {
        return this.getCheckedOutBy() == null;
    }

    public String getInformation() {
        return String.format("Book Name: %s\nAuthor: %s\nGenre: %s\nBook ID: %s\nMinimum Age: %s\nCheckout Duration: %s\nIs Available: %s\n",
                name, author, genre, bookId, minimumAge, checkoutDuration, this.getCheckedOutBy() == null ? "Yes" : "No");
    }

    public String toString() {
        return this.name;
    }
}
