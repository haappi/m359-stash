package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static io.github.haappi.battleGame.Utils.getRandomInteger;
import static io.github.haappi.battleGame.Utils.round;

public class PermUpgrades {
    private final Player player = HelloApplication.getInstance().getPlayer();
    @FXML
    protected Text playerBalance;
    @FXML
    protected ListView<String> statsAndPrice;

    @FXML
    protected void initialize() {
        playerBalance.setText(String.valueOf(player.getBankBalance()));
        setStuff();
    }

    private void setStuff() {
        statsAndPrice.getItems().clear();
        ArrayList<String> stats = (ArrayList<String>) Utils.playerStatsToList(HelloApplication.getInstance().getPlayer().getPlayerDataAsString());
        stats.add("Health: " + round(player.getMaxHealth()));
        stats.remove("Class: " + player.getClazz());
        for (String stat : stats) {
            try {
                statsAndPrice.getItems().add(stat + " - $" + Utils.calculateUpgradePrice(stat.split(": ")[0], Double.parseDouble(stat.split(": ")[1])));
            } catch (Exception ignored) {

            }
        }
    }


    @FXML
    protected void buyOne() {
        String stat = statsAndPrice.getSelectionModel().getSelectedItem();
        if (stat != null) {
            String statName = stat.split(": ")[0];
            double statValue = Double.parseDouble(stat.split(": ")[1].split(" -")[0]);
            double price = Utils.calculateUpgradePrice(statName, statValue);
            if (player.getBankBalance() >= price) {
                player.setBankBalance(player.getBankBalance() - price);
                playerBalance.setText("Bought " + statName + " for $" + price + "\nBalance Left:" + round(player.getBankBalance()));
                switch (statName.toLowerCase()) {
                    case "attack" -> player.setAttack(player.getAttack() + getRandomInteger(1, 3));
                    case "defense" -> player.setDefense(player.getDefense() + getRandomInteger(1, 3));
                    case "speed" -> player.setSpeed(player.getSpeed() + getRandomInteger(1, 3));
                    case "health" -> player.setMaxHealth(player.getMaxHealth() + getRandomInteger(1, 3));
                }
            }
        }
        setStuff();
    }

    @FXML
    protected void mainMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("main-menu");
    }
}
