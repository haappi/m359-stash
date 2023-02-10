package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

import io.github.haappi.restaurant_game.Tiles.FloorTile;
import io.github.haappi.restaurant_game.Tiles.Tile;

import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class RestaurantView {
    @Expose private Tile[][] restaurantTiles;
    @Expose public GridPane restaurantView = new GridPane();

    public void initializeClass(int size) {
        this.restaurantTiles = new Tile[size][size];

        for (int i = 0; i < restaurantTiles.length; i++) {
            for (int j = 0; j < restaurantTiles[i].length; j++) {
                restaurantTiles[i][j] = new FloorTile(Color.BEIGE, i, j);
                restaurantView.add(restaurantTiles[i][j], j, i);
            }
        }
    }

    public RestaurantView(Tile[][] restaurantTiles) {
        this.restaurantTiles = restaurantTiles;
    }

    // make all emppty tiles a floor tile
    // split any tables into 2 tiles, and a table into 4 (or two, depending on its orientation)
    @Expose
    public ListView<Upgrades>
            upgradesMenu; // make these image viwes with on clickls & flooring tiles and wghatevcer
}
