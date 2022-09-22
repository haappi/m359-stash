package io.github.haappi.arraysProject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static io.github.haappi.arraysProject.Utils.getCountAndPositionOf;

public class HelloController {
  @FXML private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
    int[] thing = {1, 2, 3, 4, 5};
    System.out.println(getCountAndPositionOf(1, thing));
  }


}
