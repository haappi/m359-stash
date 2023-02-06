package io.github.haappi.restaurant_game;

import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class RestaurantView {
    private final Tile[][] restaurantTiles;
    public GridPane resturantPane;

    public RestaurantView(int size) {
        this.restaurantTiles = new Tile[size][size];
    }

    public RestaurantView(Tile[][] restaurantTiles) {
        this.restaurantTiles = restaurantTiles;
    }

    // make all emppty tiles a floor tile
    // split any tables into 2 tiles, and a table into 4 (or two, depending on its orientation)
    public ListView
            listview; // make these image viwes with on clickls & flooring tiles and wghatevcer
}
