package io.github.haappi.wordSearchTemplate;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.wordSearchTemplate.Utils.fillBoardWithWords;

public class HelloController {
    @FXML
    protected GridPane searchBoard;
    @FXML
    protected AnchorPane anchorPane;
    Text[][] listOWords;
    ArrayList<ClickedLetter> clickedLetters = new ArrayList<>();

    @FXML
    protected void handleClickMe() {
        listOWords = new Text[4][4]; //fixme ([3][3]) this doesn't work for 3 letter words.
//        ArrayList<String> listOfWords = Utils.getWordBankFromFileAsArray("ok.txt");
        ArrayList<String> listOfWords = new ArrayList<>(List.of("cat", "bat"));
        if (listOfWords == null) {
            return; // no words were loaded. either from file not existing or read issues
        }

        for (int i = 0; i < listOWords.length; i++) {
            for (int j = 0; j < listOWords[i].length; j++) {
//              Text text = new Text(Utils.alphabet[Utils.getRandInt(0, 25)]);
                Text text = new Text("*");
                text.setFont(Font.font(20));

                listOWords[i][j] = text;

                text.setOnMouseDragEntered(event -> {
                    System.out.println("a"); // fixme this borked somehow.
                    text.setFill(Color.RED); // https://stackoverflow.com/questions/29453467/javafx-setting-background-color-for-text-controls
                    clickedLetters.add(new ClickedLetter(text));
//                    text.setStyle("-fx-highlight-fill: #ADFF2F; -fx-highlight-text-fill: #B22222; -fx-font-size: 18px;");

                });
                // text.setStrikethrough(true);
                // basically the same as setting the onAction for the button, but only for a drag, an d doesn't require me to create a whole variable for it
                // used this to get the specific event i should be using: https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
                searchBoard.add(text, j, i);
            }

        }
        listOWords = fillBoardWithWords(listOWords, listOfWords, searchBoard);
        Utils.fillBoardRadnomly(listOWords);

        anchorPane.setOnMouseDragExited( // I can add events to the AnchorPane also, and seeing whenever I released the drag button
                eventt -> { // i made this on my own. no complaints
                    eventt.consume(); // this is to make sure that the event doesn't get passed to the other nodes (gridpane)
                    ArrayList<Text> clicked = new ArrayList<>();
                    for (int i = 0; i < listOWords.length; i++) {
                        for (int j = 0; j < listOWords[i].length; j++) {
                            if (listOWords[i][j].getFill() == Color.RED) {
                                clicked.add(listOWords[i][j]);
                            }
                            listOWords[i][j].setFill(Color.BLACK);
                        }
                    }
                    clicked.forEach(rect -> rect.setText("bobster"));
                });
    }
}
