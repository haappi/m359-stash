package io.github.haappi.library;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.library.Utils.*;

public class LibraryController {
    private final static ArrayList<Book> books = new ArrayList<>();
    public ListView<Book> personBookView;
    @FXML
    protected Text userInformationOutput;
    //    public ListView<String> userInformationView;
    @FXML
    protected ListView<Person> personView;
    @FXML
    protected ListView<Book> bookView;
    @FXML
    protected Text currentBookPage;
    @FXML
    protected Button selectButton;
    @FXML
    protected TextField input;
    @FXML
    protected Text bookInfo;
    @FXML
    protected Text booksOut;

    private final Person person1;
    private final Person person2;
    private final Person person3;

    private Person selectedPerson;
    public LibraryController() {
        person1 = new Person.PersonBuilder("John Doe").age(18).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Horror").build();
        person2 = new Person.PersonBuilder("Jane Doe").age(16).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Romance").build();
        person3 = new Person.PersonBuilder("John Smith").age(12).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Mystery").build();
    }

    @FXML
    protected void initialize() {
        personView.getItems().add(person1);
        personView.getItems().add(person2);
        personView.getItems().add(person3);

        List<String> usedBookNames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String bookName = Utils.getRandomElement(HelloApplication.bookNames);
            while (usedBookNames.contains(bookName)) {
                bookName = Utils.getRandomElement(HelloApplication.bookNames);
            }
            books.add(new Book(bookName, Utils.getRandomElement(HelloApplication.authorNames), Utils.getRandomElement(HelloApplication.genreNames)));
            usedBookNames.add(bookName);
        }

        bookView.getItems().addAll(getBooksToAdd(books));

//        userInformationView.setEditable(true);
//        userInformationView.setCellFactory(TextFieldListCell.forListView()); // https://stackoverflow.com/questions/53692517/javafx-set-listview-to-be-editable
        // Just wanted to not use a Text so I can easily edit more stuff yknow
    }



    @FXML
    protected void onUserSelect(MouseEvent mouseEvent) {
        ListView<Person> source = (ListView<Person>) mouseEvent.getSource();
        Person selectedPerson = source.getSelectionModel().getSelectedItem();
//        source.getSelectionModel().clearSelection();
        if (selectedPerson != null) {
            this.selectedPerson = selectedPerson;
            userInformationOutput.setText(listToString(selectedPerson.getPersonInfo(), "\n"));

            updatePersonBookView(this.selectedPerson);

            bookView.getItems().clear();
            bookView.getItems().addAll(getBooksToAdd(books));
        }
    }

    @FXML
    protected void onBookSelect(MouseEvent mouseEvent) {
        ListView<Book> source = (ListView<Book>) mouseEvent.getSource();
        Book book = source.getSelectionModel().getSelectedItem();
        if (book == null) {
            return;
        }
        bookInfo.setText(book.getInformation());
        if (this.selectedPerson != null) {
            this.selectedPerson.checkoutBook(book);
            updatePersonBookView(this.selectedPerson);
        }
        bookView.getItems().clear();
        bookView.getItems().addAll(getBooksToAdd(books));
    }

    @FXML
    protected void onPersonBookSelect(MouseEvent mouseEvent) {
        ListView<Book> source = (ListView<Book>) mouseEvent.getSource();
        Book book = source.getSelectionModel().getSelectedItem();
        if (book == null) {
            return;
        }
        bookInfo.setText(book.getInformation());
        if (this.selectedPerson != null) {
            this.selectedPerson.returnBook(book);
            updatePersonBookView(this.selectedPerson);
        }
        bookView.getItems().clear();
        bookView.getItems().addAll(getBooksToAdd(books));


    }

    private void updatePersonBookView(@Nullable Person person) {
        if (person == null) {
            return;
        }
        personBookView.getItems().clear();
        personBookView.getItems().add(person.getBook1());
        personBookView.getItems().add(person.getBook2());
        personBookView.getItems().add(person.getBook3());


    }
}
