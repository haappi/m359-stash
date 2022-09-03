package io.github.haappi.library;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Book {
    private final String bookName;
    private final String author;
    private final String genre;
    private final Long bookId;
    private final Integer minimumAge = 0;
    private final Integer checkoutDuration = 14;
    private boolean isAvailable = true;
    private Integer checkoutTimeRemaining = 0;

    private Book(String name, String author, String genre) {
        this.bookName = name;
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

    public String getBookName() {
        return bookName;
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
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String toString() {
        return String.format("Book Name: %s\nAuthor: %s\nGenre: %s\nBook ID: %s\nMinimum Age: %s\nCheckout Duration: %s\nIs Available: %s\n",
                bookName, author, genre, bookId, minimumAge, checkoutDuration, isAvailable);
    }
}
