package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.City;
import io.github.haappi.BoardGame.PlayerTurn;
import io.github.haappi.BoardGame.Utils;
import io.github.haappi.BoardGame.Wrapper;

import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class Drive extends Action {
    public Drive(PlayerTurn playerTurn) {
        super("Drive", playerTurn);
        // mvoe from your current city to any adjacent city
    }

    @Override
    public void doAction() {
        super.doAction();
        ListView<City> cities = new ListView<>();
        System.out.println(getPlayer().getGame().getCities());
        cities.getItems()
                .addAll(getPlayer().getGame().getAdjacentCities(getPlayer().getCurrentCity()));
        ArrayList<Object> thingz = Utils.getTextInput("Drive", cities);
        TextInputDialog td = (TextInputDialog) thingz.get(0);
        td.showAndWait();
        Wrapper wrapper = (Wrapper) thingz.get(1);
        City city = (City) wrapper.get();
        if (city != null) {
            getPlayer().setCurrentCity(city);
        }
    }
}
