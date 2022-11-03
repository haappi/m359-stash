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

import java.util.ArrayList;

public class HelloController {
  Text[][] texts; // todo make sure to set the size of it later (new Button[x][x])

  @FXML
  protected GridPane searchBoard;

  @FXML
  protected AnchorPane anchorPane;

  public Button buttonn;

  @FXML
  protected void initialize() {
    searchBoard.setOnDragDetected(
            event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                event.consume();
                searchBoard.startFullDrag();
              }
            });
  }

  @FXML
  protected void handleClickMe(ActionEvent event) {
    searchBoard.setGridLinesVisible(true);

    texts = new Text[5][5];


    EventHandler<ActionEvent> z = new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.out.println(event);
        Button button = (Button) event.getSource();
        button.setText("jfsudjhfusd");
        int row = GridPane.getRowIndex(button);
        int collum = GridPane.getColumnIndex(button);
        System.out.println(row + ", " + collum);
      }
    };

    for (int i = 0; i < texts.length; i++) {
      for (int j = 0; j < texts[i].length; j++) {
        Text text = new Text(Utils.alphabet[Utils.getRandInt(0, 25)]);
        texts[i][j] = text;

        text.setOnMouseDragEntered(eventt -> text.setFill(Color.RED));
        // basically the same as setting the onAction for the button, but only for a drag, and doesn't require me to create a whole variable for it
        // used this to get the specific event i should be using: https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
        searchBoard.add(text, j, i);
      }
    }

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
