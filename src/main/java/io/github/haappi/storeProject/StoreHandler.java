package io.github.haappi.storeProject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;

public class StoreHandler {
  public StoreHandler() {
    System.out.println("hi");
  }

  @FXML
  public void initialize() {
    System.out.println("hii");
  }
  @FXML private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }
  /*
  Random number everytime program inits
  When returning change, show the user the actual cash (54 -> 2 twenties, 1 ten, 4 dollars)
   */
}
