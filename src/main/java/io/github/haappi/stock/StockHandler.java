package io.github.haappi.stock;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import static io.github.haappi.stock.Utils.*;

public class StockHandler {
    private final Person person;
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

    public StockHandler() {
        Stock stockOne = new Stock("Not A Space Agency", getRandomPrice(5.00, 30.00));
        Stock stockTwo = new Stock("Identified Flying Objects", getRandomPrice(5.00, 30.00));
        person = new Person(JOptionPane.showInputDialog("What is your name?"), (double) getRandomInteger(600, 700));
        person.setStockOne(stockOne);
        person.setStockTwo(stockTwo);
    }


    @FXML
    protected void initialize() {
        welcome.setText("Welcome, " + person.getName() + "!");
        updateWalletAndWorth();

        stockA.setText(getStockFormatted(person.getStockOne(), person.getStockOneAmount()));
        stockB.setText(getStockFormatted(person.getStockTwo(), person.getStockTwoAmount()));

        int length = Math.max(stockA.getText().length(), stockB.getText().length()); // get the longest string length

        aIncrease.setLayoutX(aIncrease.getLayoutX() + length * 3.9); // center-ish the text
        bIncrease.setLayoutX(bIncrease.getLayoutX() + length * 3.9); // center-ish the text

        updateStockPrice(person.getStockOne());
        updateStockPrice(person.getStockTwo());
        updateStuff();
    }

    private void updateWalletAndWorth() {
        netWorth.setText(String.format("Net Worth: $%.2f", person.getNetworth()));
        wallet.setText(String.format("Wallet: $%.2f", person.getWallet()));

        stockA.setText(getStockFormatted(person.getStockOne(), person.getStockOneAmount()));
        stockB.setText(getStockFormatted(person.getStockTwo(), person.getStockTwoAmount()));
    }

    @FXML
    protected void decreaseStockA() {
        recent.setText(getRecentMessage(recent.getText(), person.changeStockOne(-1)));
        updateWalletAndWorth();
    }

    @FXML
    protected void decreaseStockB() {
        recent.setText(getRecentMessage(recent.getText(), person.changeStockTwo(-1)));
        updateWalletAndWorth();
    }

    @FXML
    protected void increaseStockA() {
        recent.setText(getRecentMessage(recent.getText(), person.changeStockOne(1)));
        updateWalletAndWorth();
    }

    @FXML
    protected void increaseStockB() {
        recent.setText(getRecentMessage(recent.getText(), person.changeStockTwo(1)));
        updateWalletAndWorth();
    }

    private void updateStuff() {
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateWalletAndWorth();
            }
        }, 0L, 1000); // every second
    }

    private void updateStockPrice(Stock stock) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                double oldPrice = stock.getPrice();
                stock.setPrice(stock.getPrice() + getRandomPrice(-6.0, 6.0));
                recent.setText(getRecentMessage(recent.getText(), getStockPrice(stock, oldPrice)));
            }
        }, 1000L * getRandomInteger(5, 10), 1000 * 15); // every 15 seconds
    }
}
