package io.github.haappi.library;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.*;

import static io.github.haappi.library.Utils.*;

public class LibraryController {
    private final static ArrayList<Book> books = new ArrayList<>();
    private final static ArrayList<Person> persons = new ArrayList<>();
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

    private Person selectedPerson;
    public LibraryController() {
        persons.add(new Person.PersonBuilder("John Doe").age(18).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Horror").build());
        persons.add(new Person.PersonBuilder("Jane Doe").age(16).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Romance").build());
        persons.add(new Person.PersonBuilder("John Smith").age(12).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Mystery").build());
        persons.add(new Person.PersonBuilder("Jane Smith").age(10).bookCheckoutLimit(getRandomNumber(1, 3)).genrePreference("Fantasy").build());


    }

    @FXML
    protected void initialize() {
        personView.getItems().addAll(persons);

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
        updateBookDueTime(books);

//        userInformationView.setEditable(true);
//        userInformationView.setCellFactory(TextFieldListCell.forListView()); // https://stackoverflow.com/questions/53692517/javafx-set-listview-to-be-editable
        // Just wanted to not use a Text so I can easily edit more stuff yknow
    }


    private void updateBookDueTime(List<Book> books) {
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Iterator<Book> bookIterator = books.iterator();
                        while (bookIterator.hasNext()) { // I think(?) i can do it this way
                            Book book = bookIterator.next();
                            if (book.getCheckoutTimeRemaining() > 0) {
                                System.out.println(book);
                                book.setCheckoutTimeRemaining(book.getCheckoutTimeRemaining() - 1);
                            } else {
                                if (book.getCheckedOutBy() != null) {
                                    book.getCheckedOutBy().setCanCheckout(false);
                                }
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
//        source.getSelectionModel().clearSelection();
        if (selectedPerson != null) {
            this.selectedPerson = selectedPerson;
            userInformationOutput.setText(listToString(selectedPerson.getPersonInfo(), "\n"));
            personBookView.getItems().clear();
            personBookView.getItems().addAll(getBooksToAdd(selectedPerson.getBooksCheckedOut()));
        }
        bookView.getItems().clear();
        bookView.getItems().addAll(getBooksToAdd(books));

    }

    @FXML
    protected void onBookSelect(MouseEvent mouseEvent) {
        ListView<Book> source = (ListView<Book>) mouseEvent.getSource();
        Book book = source.getSelectionModel().getSelectedItem();
        bookInfo.setText(book.getInformation());

        if (this.selectedPerson != null) {
            this.selectedPerson.checkoutBook(book);
        }
    }
}
