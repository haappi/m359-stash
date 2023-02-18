package io.github.haappi.restaurant_game.Tiles;

import io.github.haappi.restaurant_game.Building;
import io.github.haappi.restaurant_game.Party;
import io.github.haappi.restaurant_game.Staff;
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
        for (Staff staff : building.getStaff()) {
            if (staff.getParty() == occupyingParty) {
                staff.setParty(null);
            }
            staff.setSalary(staff.getSalary() - 1);
            staff.setHappiness(staff.getHappiness() - 1);
        }
        building.setRating(building.getRating() - .5 * occupyingParty.getSize());
    }
}
