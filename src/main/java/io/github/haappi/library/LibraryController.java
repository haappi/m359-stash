package io.github.haappi.library;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static io.github.haappi.library.Utils.getRandomNumber;

public class LibraryController {
    private final static ArrayList<Book> books = new ArrayList<>();
    private final static ArrayList<Person> persons = new ArrayList<>();
    @FXML
    protected Text outut;

    public LibraryController() {
        persons.add(new Person.PersonBuilder("John Doe").age(18).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Horror").build());
        persons.add(new Person.PersonBuilder("Jane Doe").age(16).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Romance").build());
        persons.add(new Person.PersonBuilder("John Smith").age(12).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Mystery").build());
        persons.add(new Person.PersonBuilder("Jane Smith").age(10).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Fantasy").build());

        System.out.println(persons.get(0).getLibraryCardNumber());
    }

    private void updateBookDueTime(Book book) {
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (book.getCheckoutTimeRemaining() > 0) {
                            book.setCheckoutTimeRemaining(book.getCheckoutTimeRemaining() - 1);
                        } else {

                        }
                    }
                },
                0L,
                1000); // every second, decrement it.
    }
}
