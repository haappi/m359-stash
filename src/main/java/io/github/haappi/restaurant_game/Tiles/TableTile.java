package io.github.haappi.restaurant_game.Tiles;

import io.github.haappi.restaurant_game.Party;

import javafx.scene.paint.Color;

public class TableTile extends Tile {
    private Party occupyingParty;

    public TableTile(Color color, int x, int y, int prefWidthHeight) {
        super(color, x, y, prefWidthHeight);
    }

    public Party getOccupyingParty() {
        return occupyingParty;
    }

    public void setOccupyingParty(Party occupyingParty) {
        this.occupyingParty = occupyingParty;
    }
}
