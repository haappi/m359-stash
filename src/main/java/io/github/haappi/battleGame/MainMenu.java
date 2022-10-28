package io.github.haappi.battleGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainMenu {
    @FXML
    protected ListView<String> statView;

    @FXML
    public void initialize() {
        // initialize function calls everytime i load the class.
        statView.getItems().addAll(Utils.playerStatsToList(HelloApplication.getInstance().getPlayer().getPlayerDataAsString()));
    }

    @FXML
    protected void openShop() {
        HelloApplication.getInstance().setStageScene("store-view");
    }

    @FXML
    protected void goToBattle(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("battle-view");
    }

    @FXML
    protected void quitGame(ActionEvent actionEvent) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("src/main/resources/battle-data.txt")) {
            for (BattleData battleData : HelloApplication.getInstance().getBattleData()) {
                out.println(battleData);
            }
        }

        try (PrintWriter out = new PrintWriter("src/main/resources/player-data.txt")) {
            out.println(HelloApplication.getInstance().getPlayer().getPlayerDataAsString());
        }

        Platform.exit(); // https://stackoverflow.com/questions/12153622/how-to-close-a-javafx-application-on-window-close
    }

    @FXML
    protected void viewBattleHistory(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("battle-stats");
    }

    @FXML
    protected void openInventory(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("inventory-view");
    }

    public void openCasino(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("tic-tac-toe");
    }

    @FXML
    protected void upgradeMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("perm-upgrades");
    }
}
