package io.github.haappi.restaurant_game;

import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class RestaurantView {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    public GridPane resturantPane;

    // make all emppty tiles a floor tile
    // split any tables into 2 tiles, and a table into 4 (or two, depending on its orientation)
    public ListView
            listview; // make these image viwes with on clickls & flooring tiles and wghatevcer
}
