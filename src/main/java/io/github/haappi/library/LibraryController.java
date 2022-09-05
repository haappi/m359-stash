package io.github.haappi.library;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static io.github.haappi.library.Utils.getRandomNumber;
import static io.github.haappi.library.Utils.listToString;

public class LibraryController {
    private final static ArrayList<Book> books = new ArrayList<>();
    private final static ArrayList<Person> persons = new ArrayList<>();
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

    public LibraryController() {
        persons.add(new Person.PersonBuilder("John Doe").age(18).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Horror").build());
        persons.add(new Person.PersonBuilder("Jane Doe").age(16).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Romance").build());
        persons.add(new Person.PersonBuilder("John Smith").age(12).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Mystery").build());
        persons.add(new Person.PersonBuilder("Jane Smith").age(10).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Fantasy").build());

        List<String> usedBookNames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String bookName = Utils.getRandomElement(HelloApplication.bookNames);
            while (usedBookNames.contains(bookName)) {
                bookName = Utils.getRandomElement(HelloApplication.bookNames);
            }
            usedBookNames.add(bookName);
            books.add(new Book(bookName, Utils.getRandomElement(HelloApplication.authorNames), Utils.getRandomElement(HelloApplication.genreNames)));
        }
    }

    @FXML
    protected void initialize() {
        personView.getItems().addAll(persons);
        addBooks();

//        userInformationView.setEditable(true);
//        userInformationView.setCellFactory(TextFieldListCell.forListView()); // https://stackoverflow.com/questions/53692517/javafx-set-listview-to-be-editable
        // Just wanted to not use a Text so I can easily edit more stuff yknow
    }

    private void addBooks() {
        for (Book book : books) {
            if (book.isAvailable()) {
                bookView.getItems().add(book);
            }
        }
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
                            if (book.getCheckedOutBy() != null) {
                                book.getCheckedOutBy().setCanCheckout(false);
                            }
                        }
                    }
                },
                0L,
                1000); // every second, decrement it.
    }


    @FXML
    protected void onUserSelect(MouseEvent mouseEvent) {
        ListView<Person> source = (ListView<Person>) mouseEvent.getSource();
        Person selectedPerson = source.getSelectionModel().getSelectedItem();
        userInformationOutput.setText(listToString(selectedPerson.getPersonInfo(), "\n"));
    }

    @FXML
    protected void onBookSelect(MouseEvent mouseEvent) {
        ListView<Book> source = (ListView<Book>) mouseEvent.getSource();
        Book book = source.getSelectionModel().getSelectedItem();
        bookInfo.setText(book.getInformation());
    }
}
