package io.github.haappi.library;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class LibraryController {
    private final static ArrayList<Book> books = new ArrayList<>();
    private final static ArrayList<Person> persons = new ArrayList<>();
    @FXML
    protected Text outut;

    public LibraryController() {
        persons.add(new Person.PersonBuilder("John Doe").age(18).bookCheckoutLimit(2).genrePreference("Horror").build());
        persons.add(new Person.PersonBuilder("Jane Doe").age(16).bookCheckoutLimit(1).genrePreference("Romance").build());
        persons.add(new Person.PersonBuilder("John Smith").age(12).bookCheckoutLimit(1).genrePreference("Mystery").build());
        persons.add(new Person.PersonBuilder("Jane Smith").age(10).bookCheckoutLimit(1).genrePreference("Fantasy").build());

        System.out.println(persons.get(0).getLibraryCardNumber());
    }
}
