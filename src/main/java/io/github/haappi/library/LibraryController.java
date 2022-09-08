package io.github.haappi.library;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.library.Utils.getBooksToAdd;
import static io.github.haappi.library.Utils.getRandomNumber;

public class LibraryController {
    private final static ArrayList<Book> books = new ArrayList<>();
    private final Person person1;
    private final Person person2;
    private final Person person3;
    private final List<Integer> editableFields = List.of(0, 1, 5); // 0 = name, 1 = age, 5 = genre preference
    public ListView<Book> personBookView;
    @FXML
    protected ImageView imageView;
    @FXML
    protected Button higherLimitButton;
    @FXML
    protected Text latestMessage;
    @FXML
    protected Text personInformation;
    @FXML
    protected ListView<String> userInformationView;
    @FXML
    protected Text userBooksOut;
    @FXML
    protected ListView<Person> personView;
    @FXML
    protected ListView<Book> bookView;
    @FXML
    protected Text currentBookPage;
    @FXML
    protected TextField input;
    @FXML
    protected Text bookInfo;
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

        userInformationView.setEditable(true);
        userInformationView.setCellFactory(TextFieldListCell.forListView()); // https://stackoverflow.com/questions/53692517/javafx-set-listview-to-be-editable
    }


    @SuppressWarnings("unchecked")
    @FXML
    protected void onUserSelect(MouseEvent mouseEvent) {
        ListView<Person> source = (ListView<Person>) mouseEvent.getSource();
        Person selectedPerson = source.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            this.selectedPerson = selectedPerson;
            updatePersonInformation();
            updatePersonBookView(this.selectedPerson);
            updateBookStuff();
        }
    }

    @SuppressWarnings("unchecked")
    @FXML
    protected void onBookSelect(MouseEvent mouseEvent) {
        ListView<Book> source = (ListView<Book>) mouseEvent.getSource();
        Book book = source.getSelectionModel().getSelectedItem();
        if (book == null) {
            return;
        }
        bookInfo.setText(book.getInformation());
        if (this.selectedPerson != null) {
            String returnMessage = this.selectedPerson.checkoutBook(book);
            if (!returnMessage.equals("")) {
                latestMessage.setText(returnMessage);
            } else {
                updatePersonBookView(this.selectedPerson);
                latestMessage.setText(book.getName() + " has been checked out by " + this.selectedPerson.getName());
            }
            updatePersonInformation();
        }
        updateBookStuff();
    }

    @SuppressWarnings("unchecked")
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
            updatePersonInformation();
            latestMessage.setText(book.getName() + " has been returned by " + this.selectedPerson.getName());
        }
        updateBookStuff();
    }

    @FXML
    protected void onUserViewEdit(ListView.EditEvent<String> event) {
        if (this.selectedPerson == null) {
            return;
        }
        int index = event.getIndex();
        if (editableFields.contains(index)) {
            String newValue = event.getNewValue();
            switch (index) {
                case 0 -> {
                    this.selectedPerson.setName(newValue);
                    latestMessage.setText(this.selectedPerson.getName() + "'s Name changed to " + newValue);
                }
                case 1 -> {
                    this.selectedPerson.setAge(Integer.parseInt(newValue));
                    latestMessage.setText(this.selectedPerson.getName() + "'s Age changed to " + newValue);
                }
                case 5 -> {
                    this.selectedPerson.setGenrePreference(newValue);
                    latestMessage.setText(this.selectedPerson.getName() + "'s Genre Preference changed to " + newValue);
                }
            }
            updatePersonInformation();
        }
    }

    private void updateBookStuff() {
        bookView.getItems().clear();
        bookView.getItems().addAll(getBooksToAdd(books));
    }

    private void updatePersonBookView(@Nullable Person person) {
        if (person == null) {
            return;
        }
        updatePersonInformation();

    }

    private void updatePersonInformation() {
        this.updatePersonInformation(this.selectedPerson);
    }

    private void updatePersonInformation(Person person) {
        personInformation.setVisible(true);
        userInformationView.setVisible(true);
        personBookView.setVisible(true);
        userBooksOut.setVisible(true);
        higherLimitButton.setVisible(true);

        if (person.getFineDue() > 0) {
            input.setVisible(true);
            input.setPromptText("Enter amount to pay");
        } else {
            input.setVisible(false);
        }

        userInformationView.getItems().clear();
        userInformationView.getItems().addAll(person.getPersonInfo());
        userBooksOut.setText(person.getName() + "'s books checked out.");
        personInformation.setText(person.getName() + "'s information");

        personBookView.getItems().clear();
        personBookView.getItems().add(person.getBook1());
        personBookView.getItems().add(person.getBook2());
        personBookView.getItems().add(person.getBook3());
        imageView.setImage(person.getImage());
    }

    @FXML
    protected void higherLimit() {
        if (this.selectedPerson == null) {
            return;
        }
        int number = getRandomNumber(1, 3);
        if (number == 3) {
            latestMessage.setText(this.selectedPerson.getName() + " has been approved for a higher limit!");
            this.selectedPerson.setBookCheckoutLimit(this.selectedPerson.getBookCheckoutLimit() + 1);
        } else {
            latestMessage.setText(this.selectedPerson.getName() + " has not been approved for a higher limit.");
        }
        updatePersonInformation();
    }

    @FXML
    protected void inputButtonSlapped() {
        if (this.selectedPerson == null) {
            return;
        }
        String inputText = input.getText();
        if (inputText.equals("")) {
            return;
        }
        Double amount = Double.parseDouble(inputText);
        if (amount > this.selectedPerson.getFineDue()) {
            latestMessage.setText("You can't pay more than you owe!");
            return;
        }
        this.selectedPerson.setFineDue(this.selectedPerson.getFineDue() - amount);
        latestMessage.setText("You have paid " + amount + " dollars.");
        updatePersonInformation();
    }
}
