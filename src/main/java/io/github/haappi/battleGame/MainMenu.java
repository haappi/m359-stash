package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainMenu {
    @FXML
    protected ListView<String> statView;

    @FXML
    public void initialize() {
        // initialize function calls everytime i load the class.
        statView.getItems().addAll(Utils.playerStatsToList(HelloApplication.getInstance().getPlayer().getPlayerDataAsString()));
    }

    @FXML protected void openShop() {
        HelloApplication.getInstance().setStageScene("store-view");
    }

    @FXML protected void goToBattle(ActionEvent actionEvent) {
    }

    @FXML protected void quitGame(ActionEvent actionEvent) {
    }

    @FXML protected void viewBattleHistory(ActionEvent actionEvent) {
    }

    @FXML protected void openInventory(ActionEvent actionEvent) {
    }
}
