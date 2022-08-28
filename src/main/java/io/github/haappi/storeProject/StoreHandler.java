package io.github.haappi.storeProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StoreHandler {
  @FXML
  protected Text currentOperation;
  @FXML
  protected Text shoppingCart;
  @FXML
  protected TextField checkout;
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
        button.setText(getRandomItem() + " - $" + getRandomNumber(5, 15));
    });

    output.setText("Checkout"); // this successfully changes the value of the Text without it complaining
  }

  private String getFormattedItem(String item, Integer count) {
    return "x" + count + " " + item + "\n";
  }

  private String getFormattedShoppingCart() {
    StringBuilder formattedCart = new StringBuilder(); // Basically a String, but it's mutable (can be changed)
    // With a String, I'd have to set the new value of the String to the old value of the String, and then add the new value to the old value.
    for (String item : itemsInCart.keySet()) {
      formattedCart.append(getFormattedItem(item, itemsInCart.get(item)));
    }
    return formattedCart.toString();
  }

  private void setShoppingCart() {
    shoppingCart.setText("Shopping Cart:\n\n" + getFormattedShoppingCart());
  }

  @FXML
  protected void purchaseItems() {
  }

  @FXML
  protected void addProduct(ActionEvent actionEvent) {
    String text = ((Button) actionEvent.getSource()).getText(); // Cast it to a Button, because it's a button, and I got nothing else.
    String item = text.split(" - ")[0]; // split the text at the " - " and get the first part (index 0)
    Integer count = itemsInCart.getOrDefault(item, 0) + 1; // getOrDefault returns the value of the key if it exists, otherwise it returns the default value supplied
    itemsInCart.put(item, count); // put adds the key and value to the HashMap
    currentOperation.setText("You just added " + item + " to your cart. You now have " + count + " of " + item + " in your cart.");
    setShoppingCart();
  }

  /*
  Random number everytime program inits
  When returning change, show the user the actual cash (54 -> 2 twenties, 1 ten, 4 dollars)
   */
}
