package io.github.haappi.wordSearchTemplate;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Locale;

import static io.github.haappi.wordSearchTemplate.Utils.fillBoardWithWords;

public class HelloController {
    @FXML
    protected GridPane searchBoard;
    @FXML
    protected AnchorPane anchorPane;
    Text[][] listOWords;
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
        listOWords = new Text[25][25]; //fixme ([3][3]) this doesn't work for 3 letter words.

        for (int i = 0; i < listOWords.length; i++) {
            for (int j = 0; j < listOWords[i].length; j++) {
//              Text text = new Text(Utils.alphabet[Utils.getRandInt(0, 25)]);
                Text text = new Text("*");
                text.setFont(Font.font(20));


                text.setOnMouseDragEntered(event -> {
                    boolean isAlreadyClicked = false;
                    for (ClickedLetter clickedLetter : clickedLetters) {
                        if (clickedLetter.getText().equals(text)) {
                            isAlreadyClicked = true;
                        }
                    }
                    if (isAlreadyClicked) {
                        return;
                    }
                    // todo make a way so that the user can't click on a letter that is not next to the last clicked letter.
                    // todo also a way that i can dynamically change the line in the 8 directions.
                    clickedLetters.add(new ClickedLetter(text)); // this has to be before the line after as i save the color while initializing it.
//                    text.setFill(Color.RED); // https://stackoverflow.com/questions/29453467/javafx-setting-background-color-for-text-controls
//                    text.setStroke(Color.RED);
//                    text.setStyle("-fx-highlight-fill: lightgray; -fx-highlight-text-fill: firebrick; -fx-font-size: 20px;");

                });
                listOWords[i][j] = text;
                // text.setStrikethrough(true);
                // basically the same as setting the onAction for the button, but only for a drag, an d doesn't require me to create a whole variable for it
                // used this to get the specific event i should be using: https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
                searchBoard.add(text, j, i);
            }

        }
        listOWords = fillBoardWithWords(listOWords, listOfPossibleWords, searchBoard);
        Utils.fillBoardRadnomly(listOWords);

        anchorPane.setOnMouseDragExited( // I can add events to the AnchorPane also, and seeing whenever I released the drag button
                eventt -> { // i made this on my own. no complaints
                    StringBuilder sb = new StringBuilder();
                    clickedLetters.forEach(clickedLetter -> {
                        sb.append(clickedLetter.getText().getText().toUpperCase());
                        this.searchBoard.getChildren().remove(clickedLetter.getRectangle());
                    });
                    String word = sb.toString();
                    String reversed = sb.reverse().toString();
                    if (listOfPossibleWords.contains(word) || listOfPossibleWords.contains(reversed)) {
                        clickedLetters.forEach(clickedLetter -> clickedLetter.getText().setFill(Color.PINK));
                    } else {
                        clickedLetters.forEach(clickedLetter -> clickedLetter.getText().setFill(clickedLetter.getOldColor()));
                    }
                    clickedLetters.clear();
                });
    }
}
