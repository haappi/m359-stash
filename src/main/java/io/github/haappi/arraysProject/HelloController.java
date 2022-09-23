package io.github.haappi.arraysProject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;


public class HelloController {
  @FXML private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
    ArrayList<String> nrowww = new ArrayList<>();
    nrowww.add("b");
    nrowww.add("ba");
    nrowww.add("a");
    nrowww.add("c");
    nrowww.add("bdef");

    System.out.println(Utils.shuffleAnArray(nrowww));
  }


}
