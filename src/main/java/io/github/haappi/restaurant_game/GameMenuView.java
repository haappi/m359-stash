package io.github.haappi.restaurant_game;

import javafx.application.Platform;
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
        Game game = HelloApplication.getInstance().getGameInstance();
        game.setMoney(game.getMoney() - 2000);
        if (game.getMoney() < 100) {
            System.out.println(
                    "You have lost the game! You can't afford to keep your restaurant open!");
            Platform.exit();
            System.exit(0);
        }
        game.addLocation(new Building(15));
        ownedLocations.getItems().clear();
        ownedLocations
                .getItems()
                .addAll(game.getOwnedLocations());
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
