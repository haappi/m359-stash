package io.github.haappi.storeProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public class StoreHandler {
    private final double tax = 0.08;
    private final HashMap<String, Integer> itemsInCart = new HashMap<>();
    private final List<String> groceryItemTypes = List.of("Milk", "Bread", "Eggs", "Cheese", "Apples", "Pineapple", "Cake", "Water", "Pizza", "Yogurt");
    private final List<String> usedItemTypes = new ArrayList<>();
    private final HashMap<String, Integer> itemPrices = new HashMap<>();
    @FXML
    protected Button submitButton;
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
    private List<Button> buttons;

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
     * Fix decimals not rounding to 2 places
     * - They're stuck at 1 place
     * Use more parameters in general.
     */

    private Integer getRandomNumber(int min, int max) {
        max = max + 1; // max is exclusive
        return (int) (Math.random() * (max - min) + min);
    }

    @FXML// This is basically the constructor. It's called when the FXML file is loaded, the default Java constructor is not allowed as it causes NPE's with FXML attrubutes.
    public void initialize() {
        buttons = Arrays.asList(productOne, productTwo, productThree, productFour);
        buttons.forEach(button -> { // this is known as a lambda expression - an anonymous function. Basically a function without a name.
            String item = getRandomItem();
            Integer price = getRandomNumber(1, 15);
            button.setText(item + " - $" + price); // Milk - $1
            itemPrices.put(item, price);
        });

        output.setText("Checkout"); // this successfully changes the value of the Text without it complaining
    }

    /**
     * Helper method to format the shopping cart String for a specific item.
     * @param item The item to be added to the shopping cart.
     * @param count The amount of the item to be added to the shopping cart.
     * @return  The formatted String for the shopping cart.
     */
    private String getFormattedItem(String item, Integer count) {
        return "x" + count + " " + item + "\n"; // 1x Milk\n
    }

    /**
     * A pretty awful method to round a double.
     * @param numRound the number to round
     * @return the rounded number
     */
    public double getRoundedPrice(double numRound) {
        return Math.round(numRound * 100.00) / 100.00;
    }


    /**
     * Gets the formatted String of all items in the shopping cart.
     * @param itemsInCart the HashMap of items in the shopping cart.
     * @return the formatted String of all items in the shopping cart.
     */
    private String getFormattedShoppingCart(HashMap<String, Integer> itemsInCart) {
        StringBuilder formattedCart = new StringBuilder(); // Basically a String, but it's mutable (can be changed)
        // With a String, I'd have to set the new value of the String to the old value of the String, and then add the new value to the old value.
        for (String item : itemsInCart.keySet()) {
            formattedCart.append(getFormattedItem(item, itemsInCart.get(item)));
        }
        return formattedCart.toString();
    }

    /**
     * Sets the items in the shopping cart to be viewable by the user.
     * @param itemsInCart A HashMap of the items in the shopping cart.
     */
    private void setShoppingCart(HashMap<String, Integer> itemsInCart) {
        shoppingCart.setText("Shopping Cart:\n\n" + getFormattedShoppingCart(itemsInCart));
    }

    @FXML
    protected void purchaseItems() {
        startCheckout();
    }

    /**
     * Enables inputting money into the TextField.
     */
    protected void startCheckout() {
        checkout.setDisable(false);
        submitButton.setDisable(false);
        currentOperation.setText("Purchase Items");
        setTotal(getTotalPrice(false), getTotalPrice(true));
    }

    /**
     * Simply sets the subtotal and total price.
     * @param subtotal the subtotal price.
     * @param total the total price, with tax.
     */
    protected void setTotal(double subtotal, double total) {
        output.setText("Your subtotal is $" + getRoundedPrice(subtotal) + ".\nYour total is $" + getRoundedPrice(total) + ".");

    }

    /**
     * Gets the total price of the items in the cart. Pass true if you want to get the total price with tax.
     * @param includeTax If tax should be included in the total.
     * @return The total price of the items in the cart.
     */
    private double getTotalPrice(boolean includeTax) {
        double totalPrice = 0.00;
        for (Map.Entry<String, Integer> entry : itemsInCart.entrySet()) {
            totalPrice += itemPrices.get(entry.getKey()) * entry.getValue();
        }
        if (includeTax) {
            totalPrice += totalPrice * tax;
        }
        return totalPrice;
    }

    /**
     * Add's a product to the user's shopping cart. This is saved into a HashMap, with the Item & the amount of that item.
     * @param actionEvent - The event (generally a button) that triggered this method.
     */
    @FXML
    protected void addProduct(ActionEvent actionEvent) {
        String text = ((Button) actionEvent.getSource()).getText(); // Cast it to a Button, because it's a button, and I got nothing else.
        String item = text.split(" - ")[0]; // split the text at the " - " and get the first part (index 0)
        Integer count = itemsInCart.getOrDefault(item, 0) + 1; // getOrDefault returns the value of the key if it exists, otherwise it returns the default value supplied
        itemsInCart.put(item, count); // put adds the key and value to the HashMap
        currentOperation.setText("You just added " + item + " to your cart. You now have " + count + " of " + item + " in your cart.");
        setShoppingCart(this.itemsInCart);
        setTotal(getTotalPrice(false), getTotalPrice(true));
    }

    protected boolean canAfford(double paid, double totalPrice) {
        return paid >= totalPrice;
    }

    /**
     * Handles the checkout button.
     * - Checks the TextField for a valid number & if the user can afford the total price.
     */
    @FXML
    protected void onCheckout() {
        try {
            double amount = Double.parseDouble(checkout.getText());
            if (!canAfford(amount, getTotalPrice(true))) {
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

    /**
     * This handles the change calculation. Returns the exact change in dollars & cents, and not plain numbers.
     *
     * @param paidAmount The amount the customer paid.
     *               This is the amount the customer paid, not the total price.
     * @return The change the customer should get.
     */
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
}
