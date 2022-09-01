package io.github.haappi.stock;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

import static io.github.haappi.stock.Utils.*;

public class StockHandler {
  @FXML
  protected Button aIncrease;
  @FXML
  protected Button bIncrease;
  @FXML
  protected Text wallet;
  @FXML
  protected Text netWorth;
  @FXML
  protected Text stockA;
  @FXML
  protected Text stockB;
  @FXML
  protected Text welcome;
  @FXML
  protected Text recent;

  private final Stock stockOne;
  private final Stock stockTwo;
  private final Person person;

  public StockHandler() {
    stockOne = new Stock("Not A Space Agency", getRandomPrice(0.00, 60.00));
    stockTwo = new Stock("Identified Flying Objects", getRandomPrice(0.00, 60.00));
    person = new Person(JOptionPane.showInputDialog("What is your name?"), (double) getRandomInteger(600, 700));
    person.setStockOne(stockOne);
    person.setStockTwo(stockTwo);
  }


  @FXML
  protected void initialize() {
    welcome.setText("Welcome, " + person.getName() + "!");
    wallet.setText("Wallet: $" + person.getWallet());
    netWorth.setText("Net Worth: $" + person.getNetworth());

    stockA.setText(stockOne.toString());
    stockB.setText(stockTwo.toString());

    int length = Math.max(stockA.getText().length(), stockB.getText().length()); // get the longest string length

    aIncrease.setLayoutX(aIncrease.getLayoutX() + length * 3.5); // center the text
    bIncrease.setLayoutX(bIncrease.getLayoutX() + length * 3.5); // center the text
  }

  @FXML
  protected void decreaseStockA() {
    recent.setText(getRecentMessage(recent.getText(), person.changeStockOne(-1)));
  }

  @FXML
  protected void decreaseStockB() {
  }

  @FXML
  protected void increaseStockA() {
    recent.setText(getRecentMessage(recent.getText(), person.changeStockOne(1)));
  }

  @FXML
  protected void increaseStockB() {
  }
}
