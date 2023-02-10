package io.github.haappi.restaurant_game.Tiles;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Tile extends Button {
    private final Color tileColor;
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile(Color color, int x, int y) {
        super();
        this.tileColor = color;
        this.x = x;
        this.y = y;
    }

    public Color getTileColor() {
        return tileColor;
    }

}
