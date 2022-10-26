package io.github.haappi.TicTacToe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HelloController {
  @FXML protected Text gameStatus;
  @FXML private Label welcomeText;

  private boolean player1Turn = true; // true = player 1, false = player 2

  @FXML
  private Button one, two, three, four, five, six, seven, eight, nine;

  private int[][] board = new int[3][3];

  @FXML
  protected void initialize() {
    one.setId("0_0");
    two.setId("0_1");
    three.setId("0_2");
    four.setId("1_0");
    five.setId("1_1");
    six.setId("1_2");
    seven.setId("2_0");
    eight.setId("2_1");
    nine.setId("2_2");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board[i][j] = 2; // 2 means empty
      }
    }
  }

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }

  @FXML protected void one(ActionEvent actionEvent) {
    Button button = (Button) actionEvent.getSource();
    String[] id = button.getId().split("_");
    int x = Integer.parseInt(id[0]);
    int y = Integer.parseInt(id[1]);
    board[x][y] = 1;
    button.setText(player1Turn ? "X" : "O");
    button.setDisable(true);
    checkWin();
  }


  @FXML protected void two(ActionEvent actionEvent) {

  }

  @FXML protected void three(ActionEvent actionEvent) {
  }

  @FXML protected void four(ActionEvent actionEvent) {
  }

  @FXML protected void five(ActionEvent actionEvent) {
  }

  @FXML protected void six(ActionEvent actionEvent) {
  }

  @FXML protected void seven(ActionEvent actionEvent) {
  }

  @FXML protected void eight(ActionEvent actionEvent) {
  }

  @FXML protected void nine(ActionEvent actionEvent) {
  }

  @FXML protected void nameInputted(ActionEvent actionEvent) {
  }

  private void checkWin() {

  }
}
