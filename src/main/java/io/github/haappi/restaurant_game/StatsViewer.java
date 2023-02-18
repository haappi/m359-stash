package io.github.haappi.restaurant_game;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class StatsViewer {
    public Label label;

    public void initialize() {
        Building building = HelloApplication.getInstance().getGameInstance().getCurrentBuilding();
        label.setText(building.getStats());
    }

    public void goBack(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("game-menu-view");
    }
}
