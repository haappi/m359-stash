package io.github.haappi.wordSearchTemplate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class HelloController {
  Button[][] buttons; // todo make sure to set the size of it later (new Button[x][x])

  @FXML
  protected GridPane searchBoard;

  @FXML
  protected AnchorPane anchorPane;

  public Button buttonn;

  @FXML
  protected void handleClickMe(ActionEvent event) {
    searchBoard.setGridLinesVisible(true);

    buttons = new Button[5][5];

    EventHandler<ActionEvent> z = new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.out.println(event);
        ((Button) event.getSource()).setText("jfsudjhfusd");
      }
    };

    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        Button button = new Button();
        button.setText(i + ", " + j);
        buttons[i][j] = button;
        button.setOnAction(z);
        button.setPrefSize(50, 50);
        button.setMaxSize(100, 100);
        button.setMinSize(25, 25);

//        button.hoverProperty().addListener(((observable, oldValue, newValue) -> {
//          System.out.println(button.getText());
//          // https://stackoverflow.com/questions/53831807/javafx-show-a-pane-on-mouse-hover
//        }));
        // to highlight the characters- https://stackoverflow.com/questions/60012383/mousedragged-detection-for-multiple-nodes-while-holding-the-button-javafx
        searchBoard.add(button, j, i);
      }
    }



  }



}
