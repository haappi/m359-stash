package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.City;
import io.github.haappi.BoardGame.PlayerTurn;
import io.github.haappi.BoardGame.Utils;
import io.github.haappi.BoardGame.Wrapper;

import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class CharterFlight extends Action {
    public CharterFlight(PlayerTurn playerTurn) {
        super("Charter Flight", playerTurn);
        // Charter Flight: Move to any city by discarding the city card that matches the city you
        // are in.
    }

    @Override
    public void doAction() {
        super.doAction();
        ListView<City> cities = new ListView<>();
        cities.getItems().addAll(super.getPlayer().getGame().getCities());
        cities.getItems().remove(super.getPlayer().getCurrentCity());
        ArrayList<Object> thingz = Utils.getTextInput("Charter Flight", cities);
        TextInputDialog td = (TextInputDialog) thingz.get(0);
        td.showAndWait();
        Wrapper wrapper = (Wrapper) thingz.get(1);
        City city = (City) wrapper.get();

        if (city == null) {
            super.incrementRemainingActions();
            return;
        }

        if (getPlayer().playerHasCityCard(getPlayer().getCurrentCity())) {
            getPlayer().discardACard(getPlayer().getCityCard(getPlayer().getCurrentCity()));
            getPlayer().setCurrentCity(city);
        } else {
            super.incrementRemainingActions();
        }
    }
}
