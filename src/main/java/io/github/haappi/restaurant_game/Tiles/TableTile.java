package io.github.haappi.restaurant_game.Tiles;

import io.github.haappi.restaurant_game.Building;
import io.github.haappi.restaurant_game.Party;

import javafx.scene.paint.Color;

public class TableTile extends Tile {
    private final Building building;
    private Party occupyingParty;

    public TableTile(Color color, int x, int y, int prefWidthHeight, Building building) {
        super(color, x, y, prefWidthHeight);
        this.building = building;
    }

    public Party getOccupyingParty() {
        return occupyingParty;
    }

    public void setOccupyingParty(Party occupyingParty) {
        this.occupyingParty = occupyingParty;
    }

    public void angryParty(Party occupyingParty) {
        setOccupyingParty(null);
        building.setRating(building.getRating() - .5 * occupyingParty.getSize());
    }
}
