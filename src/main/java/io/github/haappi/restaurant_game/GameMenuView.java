package io.github.haappi.restaurant_game;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GameMenuView {
    public ListView<Building> ownedLocations;
    public ListView<LocationManagerHandler> locationManager;
    public Label managingLocationLabel;
    public Button add;
    public Button remove;

    public void addResturant(ActionEvent actionEvent) {}

    public void yeetResutrant(ActionEvent actionEvent) {}
}
