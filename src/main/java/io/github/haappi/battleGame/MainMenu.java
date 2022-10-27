package io.github.haappi.battleGame;

import javafx.application.Platform;
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

    @FXML
    protected void openShop() {
        HelloApplication.getInstance().setStageScene("store-view");
    }

    @FXML
    protected void goToBattle(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("battle-view");
    }

    @FXML
    protected void quitGame(ActionEvent actionEvent) {
        // save all plater data and what not bfore actually closing the applcaition
        Platform.exit(); // https://stackoverflow.com/questions/12153622/how-to-close-a-javafx-application-on-window-close
    }

    @FXML
    protected void viewBattleHistory(ActionEvent actionEvent) {
    }

    @FXML
    protected void openInventory(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("inventory-view");
    }

    public void openCasino(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("tic-tac-toe");
    }
}
