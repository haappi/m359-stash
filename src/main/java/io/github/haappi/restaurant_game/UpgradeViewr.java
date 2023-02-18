package io.github.haappi.restaurant_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UpgradeViewr {
    private final Game game = HelloApplication.instance.getGameInstance();
    public Button dishes;
    public Button chairs;
    public Button table;
    public Label cashAmount;

    @FXML
    protected void initialize() {
        setStuff();
    }

    private void setStuff() {
        cashAmount.setText(String.valueOf(game.getMoney()));

        dishes.setText(game.getCurrentBuilding().getPlateTier() + "");
        chairs.setText(game.getCurrentBuilding().getChairTier() + "");
        table.setText(game.getCurrentBuilding().getTableTier() + "");
    }

    public void increaseDish(ActionEvent actionEvent) {
        game.setMoney(game.getMoney() - 100 * game.getCurrentBuilding().getPlateTier());
        game.getCurrentBuilding().setPlateTier(game.getCurrentBuilding().getPlateTier() + 1);
        setStuff();
    }

    public void increaseChair(ActionEvent actionEvent) {
        game.setMoney(game.getMoney() - 100 * game.getCurrentBuilding().getChairTier());
        game.getCurrentBuilding().setChairTier(game.getCurrentBuilding().getChairTier() + 1);
        setStuff();
    }

    public void increaseTable(ActionEvent actionEvent) {
        game.setMoney(game.getMoney() - 100 * game.getCurrentBuilding().getTableTier());
        game.getCurrentBuilding().setTableTier(game.getCurrentBuilding().getTableTier() + 1);
        setStuff();
    }
}
