package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

import io.github.haappi.restaurant_game.Tiles.Tile;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class FarmingView {
    @Expose private final Tile[][] farmingTiles;
    public GridPane farmTiles;
    public ListView inventoryView;

    public FarmingView(int size) {
        this.farmingTiles = new Tile[size][size];
    }

    public FarmingView(Tile[][] farmingTiles) {
        this.farmingTiles = farmingTiles;
    }

    public void driveBack(ActionEvent actionEvent) {}
}
