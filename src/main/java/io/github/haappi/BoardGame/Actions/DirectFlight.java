package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class DirectFlight extends Action {
    public DirectFlight(PlayerTurn playerTurn) {
        super("Direct Flight", playerTurn);
        // discard a city card to move to that city
    }

    @Override
    public void doAction() {
        super.doAction();
        ListView<City> cities = new ListView<>();
        for (Card city : this.getPlayer().getHand()) {
            if (city instanceof CityCard) {
                cities.getItems().add((((CityCard) city).getCity()));
            }
        }
        ArrayList<Object> thingz = Utils.getTextInput("Direct Flight", cities);
        TextInputDialog td = (TextInputDialog) thingz.get(0);
        td.showAndWait();
        Wrapper wrapper = (Wrapper) thingz.get(1);
        City city = (City) wrapper.get();
        if (city == null) {
            super.incrementRemainingActions();
            return;
        }
        getPlayer().discardACard(getPlayer().getCityCard(city));
        getPlayer().setCurrentCity(city);

    }
}
