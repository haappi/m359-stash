package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.City;
import io.github.haappi.BoardGame.PlayerTurn;
import io.github.haappi.BoardGame.Utils;
import io.github.haappi.BoardGame.Wrapper;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class ShuttleFlight extends Action {
    public ShuttleFlight(PlayerTurn playerTurn) {
        super("Shuttle Flight", playerTurn);
        // fly between two cities that have a research station
    }

    @Override
    public void doAction() {
        if (!getPlayer().getCurrentCity().isHasResearchStation()) {
            return;
        }
        super.doAction();
        ListView<City> cities = new ListView<>();
        for (City city : getPlayer().getGame().getCities()) {
            if (city.isHasResearchStation() && !city.equals(getPlayer().getCurrentCity())) {
                cities.getItems().add(city);
            }
        }
        ArrayList<Object> things = Utils.getTextInput("Shuttle Flight", cities);
        TextInputDialog td = (TextInputDialog) things.get(0);
        td.showAndWait();
        City city = (City) ((Wrapper) things.get(1)).get();
        if (city == null) {
            super.incrementRemainingActions();
            return;
        }
        getPlayer().setCurrentCity(city);


//        // make a ListView with all the cities that have a research station
//        // and let the player choose one
//        ListView<City> cities = new ListView<>();
//        for (City city : super.getPlayer().getGame().getCities()) {
//            if (city.isHasResearchStation()) {
//                cities.getItems().add(city);
//            }
//        }
//        cities.setPrefHeight(cities.getItems().size() * 69 + 2);
//        // https://stackoverflow.com/questions/17429508/how-do-you-get-javafx-listview-to-be-the-height-of-its-items
//        TextInputDialog td = new TextInputDialog("City Name");
//        cities.setOnMouseClicked(event -> {
//            if (cities.getSelectionModel().getSelectedItem() == null) {
//                return;
//            }
//            td.hide();
//            System.out.println(cities.getSelectionModel().getSelectedItem());
//        });
//        td.setTitle("Shuttle Flight");
//        td.getDialogPane().setContent(cities);
//        td.setHeaderText("Shuttle Flight to a city");
////        td.setContentText("City Name:");
////        td.showAndWait();
//        td.show();
//
//        String cityName = td.getEditor().getText();
    }

}
