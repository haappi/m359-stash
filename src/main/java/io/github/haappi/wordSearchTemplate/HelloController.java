package io.github.haappi.wordSearchTemplate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {
  // todo columns go up <-> down. rows go left <-> right
  Text[][] texts; // todo make sure to set the size of it later (new Button[x][x])

  @FXML
  protected GridPane searchBoard;

  @FXML
  protected AnchorPane anchorPane;

  public Button buttonn;

  @FXML
  protected void initialize() throws IOException {
    System.out.println(Utils.getWordBankFromFileAsArray("ok.txt"));

    searchBoard.setHgap(20);
    searchBoard.setVgap(20);
    searchBoard.setOnDragDetected(
            event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                event.consume();
                searchBoard.startFullDrag();
              }
            });
  }

  private Text[][] populateArray(Text[][] textArray, ArrayList<String> words) {
    ArrayList<String> wordsClone = new ArrayList<>(words);
    String randomWord = words.get(Utils.getRandInt(0, wordsClone.size() - 1));
    wordsClone.remove(randomWord);
    while (true) {
      int direction = Utils.getRandInt(0, 7);
      int randomRow = Utils.getRandInt(0, textArray.length - 1);
      int randomColumn = Utils.getRandInt(0, textArray[randomRow].length - 1);
//      if (textArray[randomRow][randomColumn].getText().equals("")) {
      if (true) {
        System.out.println("woo");
        textArray[randomRow][randomColumn].setText("a");
        break;
      } else {

      }
    }
    return textArray;
  }

  private boolean populateArrayWithWord(int col, int row, int direction, String word) throws Exception {
    if (direction < 0 || direction > 7) {
      throw new Exception("have fun debugging this you moron");
    }
    /*
    one - up
    two - up right
    three - right
    four - bottom right
    five - bottom
    six - bottom left
    seven - left
    eight - up left
     */
    int lengthOfWord = word.length();
    switch (direction) {
      case 0 -> {

      }
    }
    // todo return true if it works, else return false to add it back to the words array
  }

  @FXML
  protected void handleClickMe(ActionEvent event) throws IOException {
//    searchBoard.setGridLinesVisible(true);

    texts = new Text[5][5];
    ArrayList<String> listOfWords = Utils.getWordBankFromFileAsArray("ok.txt");
    if (listOfWords == null) {
      return; // no words were loaded. either from file not existing or read issues
    }


    for (int i = 0; i < texts.length; i++) {
      for (int j = 0; j < texts[i].length; j++) {
        Text text = new Text(Utils.alphabet[Utils.getRandInt(0, 25)]);
//        Text text = new Text();

        texts[i][j] = text;

        text.setOnMouseDragEntered(eventt -> {
          text.setFill(Color.RED);
          // https://stackoverflow.com/questions/29453467/javafx-setting-background-color-for-text-controls
//          text.setStyle("-fx-highlight-fill: #ADFF2F; -fx-highlight-text-fill: #B22222; -fx-font-size: 18px;");

        });
//        text.setStrikethrough(true);
        // basically the same as setting the onAction for the button, but only for a drag, and doesn't require me to create a whole variable for it
        // used this to get the specific event i should be using: https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
        searchBoard.add(text, j, i);
      }

    }
    populateArray(texts, listOfWords);

    anchorPane.setOnMouseDragExited( // I can add events to the AnchorPane also, and seeing whenever I released the drag button
            eventt -> { // i made this on my own. no complaints
              eventt.consume(); // this is to make sure that the event doesn't get passed to the other nodes (gridpane)
              ArrayList<Text> clicked = new ArrayList<>();
              for (int i = 0; i < texts.length; i++) {
                for (int j = 0; j < texts[i].length; j++) {
                  if (texts[i][j].getFill() == Color.RED) {
                    clicked.add(texts[i][j]);
                  }
                  texts[i][j].setFill(Color.BLACK);
                }
              }
              clicked.forEach(rect -> rect.setText("bobster"));
            });
    // todo
      // make it so it DOESN'T change color, but actually has a proper highlighting thing (maybe with css)
    // https://stackoverflow.com/questions/9128535/highlighting-strings-in-javafx-textarea
      // make it check on the fly that if the current drrag is valid or not. if it isn't, ignore.

  }



}
