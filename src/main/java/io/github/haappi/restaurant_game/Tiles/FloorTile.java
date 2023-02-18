package io.github.haappi.restaurant_game.Tiles;

import com.google.gson.annotations.Expose;
import javafx.scene.paint.Color;

public class FloorTile extends Tile {
    @Expose
    private boolean isSomethingOnTop = false;

    public FloorTile(Color color, int x, int y, int prefWidthHeight) {
        super(color, x, y, prefWidthHeight);
    }

    public boolean isSomethingOnTop() {
        return isSomethingOnTop;
    }

    public void setSomethingOnTop(boolean somethingOnTop) {
        isSomethingOnTop = somethingOnTop;
    }
}
