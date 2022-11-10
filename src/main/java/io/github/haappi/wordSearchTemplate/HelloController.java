package io.github.haappi.wordSearchTemplate;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

import static io.github.haappi.wordSearchTemplate.Utils.fillBoardWithWords;

public class HelloController {
    @FXML
    protected GridPane searchBoard;
    @FXML
    protected AnchorPane anchorPane;
    Label[][] listOWords;
    ArrayList<ClickedLetter> clickedLetters = new ArrayList<>();
    private final ArrayList<String> listOfPossibleWords = new ArrayList<>();

    @FXML
    protected void initialize() {
        searchBoard.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();
                        searchBoard.startFullDrag();
                    }
                });
        ArrayList<String> words = Utils.readFromFileAsArray("ok.txt");
        words.replaceAll(String::toUpperCase); // Calls toUpperCase on each String in the array.
        listOfPossibleWords.addAll(words);

    }


    @FXML
    protected void handleClickMe() {
        listOWords = new Label[25][25]; //fixme ([3][3]) this doesn't work for 3 letter words.

        for (int i = 0; i < listOWords.length; i++) {
            for (int j = 0; j < listOWords[i].length; j++) {
//              Text text = new Text(Utils.alphabet[Utils.getRandInt(0, 25)]);
                Label label = new Label("*");

                label.setPrefSize(30, 30);
                label.setFont(Font.font(20));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setAlignment(Pos.CENTER);
                label.setContentDisplay(ContentDisplay.CENTER);

                label.setOnMouseDragged(event -> {
                    boolean isAlreadyClicked = false;
                    for (ClickedLetter clickedLetter : clickedLetters) {
                        if (clickedLetter.getLabel().equals(label)) {
                            System.out.println("hi");
                            this.searchBoard.getChildren().remove(clickedLetter.getRectangle());
                            clickedLetters.remove(clickedLetter); // fixme fix the concurrent array modification error here.
                            isAlreadyClicked = true;
                        }
                    }
                    if (isAlreadyClicked) {
                        return;
                    }
                    // todo make a way so that the user can't click on a letter that is not next to the last clicked letter.
                    // todo also a way that i can dynamically change the line in the 8 directions.
                    clickedLetters.add(new ClickedLetter(label, clickedLetters.size())); // this has to be before the line after as i save the color while initializing it.
                    if (clickedLetters.size() > 2) {
                        clickedLetters.get(clickedLetters.size() - 2).getRectangle().setStyle("-fx-background-color: red; -fx-background-radius: 0 0 0 0;");
                    }

                });
                listOWords[i][j] = label;
                // text.setStrikethrough(true);
                // basically the same as setting the onAction for the button, but only for a drag, an d doesn't require me to create a whole variable for it
                // used this to get the specific event i should be using: https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
                searchBoard.add(label, j, i);
            }

        }
        listOWords = fillBoardWithWords(listOWords, listOfPossibleWords, searchBoard);
        Utils.fillBoardRadnomly(listOWords);

        anchorPane.setOnMouseDragExited( // I can add events to the AnchorPane also, and seeing whenever I released the drag button
                eventt -> { // i made this on my own. no complaints
                    StringBuilder sb = new StringBuilder();
                    clickedLetters.forEach(clickedLetter -> {
                        sb.append(clickedLetter.getLabel().getText().toUpperCase());
                        this.searchBoard.getChildren().remove(clickedLetter.getRectangle());
                    });
                    String word = sb.toString();
                    String reversed = sb.reverse().toString();
                    if (listOfPossibleWords.contains(word) || listOfPossibleWords.contains(reversed)) {
                        clickedLetters.forEach(clickedLetter -> clickedLetter.getLabel().setTextFill(Color.PINK));
                    } else {
                        clickedLetters.forEach(clickedLetter -> clickedLetter.getLabel().setTextFill(clickedLetter.getOldColor()));
                    }
                    clickedLetters.clear();
                });
    }
}
