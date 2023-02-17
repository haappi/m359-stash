package io.github.haappi.restaurant_game.Tiles;

import javafx.scene.paint.Color;

public class DecorTile extends Tile {

    //    public class DecorTilee extends Upgradeable {
    //
    //        public DecorTilee(Color color, int x, int y, int prefWidthHeight) {
    //            super(color, x, y, prefWidthHeight);
    //        }
    //
    //        super(color, x, y, prefWidthHeight);

    //    }
    //    public DecorTile(Color color, int x, int y, int prefWidthHeight) {
    //        super(color, x, y, prefWidthHeight);
    //    }

    private TileDecors tileDecor = TileDecors.NONE;

    public DecorTile(Color color, int x, int y, int prefWidthHeight) {
        super(color, x, y, prefWidthHeight);
    }

    public TileDecors getTileDecor() {
        return tileDecor;
    }

    public void setTileDecor(TileDecors tileDecor) {
        this.tileDecor = tileDecor;
    }

    public void setTileDecor(TileDecors tileDecor, Color color) {
        this.tileDecor = tileDecor;
        super.setStyle("-fx-background-color: " + color.toString().substring(2));
    }
}
