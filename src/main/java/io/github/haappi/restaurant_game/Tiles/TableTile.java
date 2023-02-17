package io.github.haappi.restaurant_game.Tiles;

import io.github.haappi.restaurant_game.Party;
import javafx.scene.paint.Color;

public class TableTile extends Tile {
    private final Party occupyingParty;

    public TableTile(Color color, int x, int y, int prefWidthHeight, Party occupyingParty) {
        super(color, x, y, prefWidthHeight);
        this.occupyingParty = occupyingParty;
    }
}
