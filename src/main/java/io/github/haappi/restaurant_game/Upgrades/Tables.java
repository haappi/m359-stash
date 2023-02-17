package io.github.haappi.restaurant_game.Upgrades;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Tables extends Upgradeable {
    private static final ArrayList<Color> tableColors =
            new ArrayList<>(List.of(Color.AQUA, Color.WHITE));

    public Tables(int currentTier) {
        super(currentTier, tableColors.size(), tableColors);
    }
}
