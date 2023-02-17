package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;
import io.github.haappi.restaurant_game.Tiles.FloorTile;
import io.github.haappi.restaurant_game.Tiles.Tile;
import io.github.haappi.restaurant_game.Upgrades.Upgradeable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class RestaurantView {
    // todo
    // https://stackoverflow.com/questions/37619867/how-to-display-gridpane-object-grid-lines-permanently-and-without-using-the-set
    public AnchorPane anchorPane;
    @Expose
    public GridPane restaurantView = new GridPane();
    // make all emppty tiles a floor tile
    // split any tables into 2 tiles, and a table into 4 (or two, depending on its orientation)
    @Expose
    public ListView<Upgradeable>
            upgradesMenu; // make these image viwes with on clickls & flooring tiles and wghatevcer
    @Expose
    private Tile[][] restaurantTiles;

    @FXML
    protected void initialize() {
        restaurantView.setStyle("-fx-background-color: #FFFFFF; -fx-grid-lines-visible: true");

        final Game game = HelloApplication.getInstance().getGameInstance();
        HelloApplication.getInstance().setCurrentPane(anchorPane);
//        initializeClass(game.getRestaurantTiles());
    }

    public void initializeClass(int size) {
        this.restaurantTiles = new Tile[size][size];

        for (int i = 0; i < restaurantTiles.length; i++) {
            for (int j = 0; j < restaurantTiles[i].length; j++) {
                restaurantTiles[i][j] = new FloorTile(Color.BEIGE, i, j, 60);
                restaurantView.add(restaurantTiles[i][j], j, i);
            }
        }
    }

    public void initializeClass(Tile[][] restaurantTiles) {
        this.restaurantTiles = restaurantTiles;

        for (int i = 0; i < restaurantTiles.length; i++) {
            for (int j = 0; j < restaurantTiles[i].length; j++) {
                restaurantView.add(restaurantTiles[i][j], j, i);
            }
        }

        // space the gridpane evenly
        for (int i = 0; i < restaurantTiles.length; i++) {
            restaurantView.getColumnConstraints().add(i, new ColumnConstraints(60));
            restaurantView.getRowConstraints().add(i, new RowConstraints(60));
        }
    }
}
