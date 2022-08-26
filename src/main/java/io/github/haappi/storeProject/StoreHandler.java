package io.github.haappi.storeProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class StoreHandler {
  @FXML
  protected Text output;

  public StoreHandler() {
    System.out.println("hi");
  }

  @FXML
  public void initialize() {
    System.out.println("hii");
    output.setText("ooh ooh ah ah"); // this successfully changes the value of the Text without it complaining
  }

  @FXML
  protected void addProductOne() {
  }
  @FXML
  protected void addProductTwo() {
  }
  @FXML
  protected void addProductThree() {
  }
  @FXML
  protected void addProductFour() {
  }
  @FXML
  protected void purchaseItems() {
  }

  /*
  Random number everytime program inits
  When returning change, show the user the actual cash (54 -> 2 twenties, 1 ten, 4 dollars)
   */
}
