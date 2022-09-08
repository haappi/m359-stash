package io.github.haappi.library;

import org.jetbrains.annotations.Nullable;

import static io.github.haappi.library.Utils.getRandomStringOfNumbers;

public class Book {
    private final String name;
    private final String author;
    private final String genre;
    private final Long bookId;
    private final Long checkoutDuration = 14 * 1000L;
    private Long dueDate = -1L; // -1L means the book is not checked out
    private Person checkedOutBy; // I'm too lazy to loop through the People array

    public Book(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.bookId = getRandomStringOfNumbers(8);
    }

    public @Nullable Person getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(@Nullable Person checkedOutBy) {
        if (checkedOutBy == null) {
            this.dueDate = -1L;
        } else {
            this.dueDate = System.currentTimeMillis() + checkoutDuration;
        }
        this.checkedOutBy = checkedOutBy;
    }

    public Long getWhenBookIsDue() {
        return dueDate;
    }

    public void checkoutBook(Person person) {
        if (person.getCanCheckout()) {
            setCheckedOutBy(person);
        }
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

    public boolean isAvailable() {
        return this.getCheckedOutBy() == null;
    }

    public String getInformation() {
        return String.format("Book Name: %s\nAuthor: %s\nGenre: %s\nBook ID: %s\nCheckout Duration: %s seconds\nIs Available: %s\n",
                this.getName(), this.getAuthor(), this.getGenre(), this.getBookId(), checkoutDuration / 1000, this.getCheckedOutBy() == null ? "Yes" : "No");
    }

    public String toString() {
        return this.name;
    }
}
