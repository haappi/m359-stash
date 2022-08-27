package io.github.haappi.storeProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StoreHandler {
  @FXML
  protected Button productOne;
  @FXML
  protected Button productTwo;
  @FXML
  protected Button productThree;
  @FXML
  protected Button productFour;
  @FXML
  protected Text output;
  private HashMap<String, Integer> itemsInCart = new HashMap<>();
  private List<Button> buttons;
  private List<String> groceryItemTypes = List.of("Milk", "Bread", "Eggs", "Cheese", "Apples", "Pineapple", "Cake", "Water", "Pizza", "Yogurt");
  private List<String> usedItemTypes = new ArrayList<>();

  private String getRandomItem() {
    int randomItem = (int) (Math.random() * groceryItemTypes.size());
    String item = groceryItemTypes.get(randomItem);
    while (usedItemTypes.contains(item)) {
      randomItem = (int) (Math.random() * groceryItemTypes.size());
      item = groceryItemTypes.get(randomItem);
    }
    usedItemTypes.add(item);
    return item;
  }

  private Integer getRandomNumber(int min, int max) {
    max = max + 1; // max is exclusive
    return (int) (Math.random() * (max - min) + min);
  }

  @FXML
  public void initialize() {
    buttons = Arrays.asList(productOne, productTwo, productThree, productFour);
    buttons.forEach(button -> { // this is known as a lambda expression - an anonymous function. Basically a function without a name.
        button.setText(getRandomItem() + " - " + getRandomNumber(5, 15));
    });

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
