package io.github.haappi.stock;

import static io.github.haappi.stock.Utils.getRandomPrice;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class StockHandler {
  @FXML protected Text wallet;
  @FXML protected Text netWorth;
  @FXML protected Text stockA;
  @FXML protected Text stockB;
  @FXML protected Text welcome;
  @FXML protected Text recent;

  private final Stock stockOne;
  private final Stock stockTwo;

  public StockHandler() {
    stockOne = new Stock("Not A Space Agency", getRandomPrice(0.00, 60.00));
    stockTwo = new Stock("Identified Flying Objects", getRandomPrice(0.00, 60.00));
  }

  @FXML
  protected void initialize() {
    stockA.setText(stockOne.toString());
    stockB.setText(stockTwo.toString());
  }

  @FXML
  protected void decreaseStockA() {}

  @FXML
  protected void decreaseStockB() {}

  @FXML
  protected void increaseStockA() {}

  @FXML
  protected void increaseStockB() {}
}
