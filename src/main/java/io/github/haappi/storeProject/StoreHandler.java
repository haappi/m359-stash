package io.github.haappi.storeProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public class StoreHandler {
  private final double tax = 0.08F;
  @FXML
  protected Text change;
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
  private final HashMap<String, Integer> itemsInCart = new HashMap<>();
  private List<Button> buttons;
  private final List<String> groceryItemTypes = List.of("Milk", "Bread", "Eggs", "Cheese", "Apples", "Pineapple", "Cake", "Water", "Pizza", "Yogurt");
  private final List<String> usedItemTypes = new ArrayList<>();
  private final HashMap<String, Integer> itemPrices = new HashMap<>();

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

  /**
   * todo
   Fix decimals not rounding to 2 places
   - They're stuck at 1 place
   Fix the output looking a bit funny
   Add colors to the output
   Use more parameters in general.
   */

  private Integer getRandomNumber(int min, int max) {
    max = max + 1; // max is exclusive
    return (int) (Math.random() * (max - min) + min);
  }

  @FXML
  public void initialize() {
    buttons = Arrays.asList(productOne, productTwo, productThree, productFour);
    buttons.forEach(button -> { // this is known as a lambda expression - an anonymous function. Basically a function without a name.
      String item = getRandomItem();
      Integer price = getRandomNumber(1, 15);
        button.setText(item + " - $" + price);
        itemPrices.put(item, price);
    });

    output.setText("Checkout"); // this successfully changes the value of the Text without it complaining
  }

  private String getFormattedItem(String item, Integer count) {
    return "x" + count + " " + item + "\n";
  }

  public double getRoundedPrice(double numRound){
    return Math.round(numRound * 100.00) / 100.00;
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
    checkout.setDisable(false);
    currentOperation.setText("Purchase Items");
    output.setText("Your subtotal is $" + getRoundedPrice(getTotalPrice(false)) + ".\nYour total is $" + getRoundedPrice(getTotalPrice(true)) + ".");
  }

  private double getTotalPrice(boolean includeTax) {
    double totalPrice = 0.00F;
    for (Map.Entry<String, Integer> entry : itemsInCart.entrySet()) {
      totalPrice += itemPrices.get(entry.getKey()) * entry.getValue();
    }
    if (includeTax) {
      totalPrice += totalPrice * tax;
    }
    return totalPrice;
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

  @FXML
  protected void onCheckout() {
    try {
        int amount = Integer.parseInt(checkout.getText());
        if (amount < getTotalPrice(true)) {
          change.setText("You can't afford that!");
          change.setFill(Color.RED);
        } else {
            change.setText(changeHandler(amount));
            change.setFill(Color.GREEN);
        }
        } catch (NumberFormatException e) {
      change.setText("Please enter a valid number.");
        change.setFill(Color.RED);
    }
  }

  protected String changeHandler(double paidAmount) {
    // Figure out how much change is due, in dollars and cents
    double changeDue = paidAmount - getTotalPrice(true);
    final double savedDue = paidAmount - getTotalPrice(true);
    // See how many twnties, tens, fives, ones, quarters, dimes, nickels, and pennies are due

    int twenties = (int) (changeDue / 20);
    changeDue -= twenties * 20;
    int tens = (int) (changeDue / 10);
    changeDue -= tens * 10;
    int fives = (int) (changeDue / 5);
    changeDue -= fives * 5;
    int ones = (int) (changeDue);
    changeDue -= ones;
    int quarters = (int) (changeDue / 0.25);
    changeDue -= quarters * 0.25;
    int dimes = (int) (changeDue / 0.10);
    changeDue -= dimes * 0.10;
    int nickels = (int) (changeDue / 0.05);
    changeDue -= nickels * 0.05;
    int pennies = (int) (changeDue / 0.01);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Your change is: \n");
    if (twenties > 0) {
      stringBuilder.append("x" + twenties + " twenties\n");
    }
    if (tens > 0) {
      stringBuilder.append("x" + tens + " tens\n");
    }
    if (fives > 0) {
      stringBuilder.append("x" + fives + " fives\n");
    }
    if (ones > 0) {
      stringBuilder.append("x" + ones + " ones\n");
    }
    if (quarters > 0) {
      stringBuilder.append("x" + quarters + " quarters\n");
    }
    if (dimes > 0) {
      stringBuilder.append("x" + dimes + " dimes\n");
    }
    if (nickels > 0) {
      stringBuilder.append("x" + nickels + " nickels\n");
    }
    if (pennies > 0) {
      stringBuilder.append("x" + pennies + " pennies\n");
    }
    stringBuilder.append("Totaling to $" + getRoundedPrice(savedDue) + ".");
    return stringBuilder.toString();


  }

  /*
  Random number everytime program inits
  When returning change, show the user the actual cash (54 -> 2 twenties, 1 ten, 4 dollars)
   */
}
