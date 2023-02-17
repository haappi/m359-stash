package io.github.haappi.restaurant_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class GameMenuView {
    public ListView<Building> ownedLocations;
    public ListView<LocationManagerHandler> locationManager;
    public Label managingLocationLabel;
    public Button add;
    public Button remove;

    @FXML
    protected void initialize() {
        ownedLocations
                .getItems()
                .addAll(HelloApplication.getInstance().getGameInstance().getOwnedLocations());
    }

    public void addResturant(ActionEvent actionEvent) {
        HelloApplication.getInstance().getGameInstance().getOwnedLocations().add(new Building(15));
        ownedLocations.getItems().clear();
        ownedLocations
                .getItems()
                .addAll(HelloApplication.getInstance().getGameInstance().getOwnedLocations());
    }

    public void yeetResutrant(ActionEvent actionEvent) {
        if (ownedLocations.getSelectionModel().getSelectedItem() != null) {
            Game game = HelloApplication.getInstance().getGameInstance();
            Building randomBuilding = ownedLocations.getSelectionModel().getSelectedItem();
            HelloApplication.getInstance()
                    .getGameInstance()
                    .getOwnedLocations()
                    .remove(randomBuilding);
            ownedLocations.getItems().clear();
            game.setMoney(
                    (int)
                            (game.getMoney()
                                    + randomBuilding.getMoney()
                                    - randomBuilding.getMoney() / 2));
            game.setMoney(game.getMoney() + (int) (500 - randomBuilding.getRating() * 75));
            ownedLocations
                    .getItems()
                    .addAll(HelloApplication.getInstance().getGameInstance().getOwnedLocations());
        }
    }

    public void onResClick(MouseEvent mouseEvent) {
        if (ownedLocations.getSelectionModel().getSelectedItem() != null) {
            locationManager.getItems().clear();
            locationManager
                    .getItems()
                    .addAll(
                            ownedLocations
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .getManagers(locationManager));
            managingLocationLabel.setText(
                    "Managing: "
                            + ownedLocations
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .getBuildingName());
        }
    }

    public void goMenuBack(ActionEvent actionEvent) {
        HelloApplication.getInstance().getGameInstance().saveProfile();
        HelloApplication.getInstance().setStageScene("huh.fxml"); // todo make this better later
    }
}
