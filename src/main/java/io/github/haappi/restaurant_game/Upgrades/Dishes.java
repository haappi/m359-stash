package io.github.haappi.restaurant_game.Upgrades;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Dishes extends Upgradeable { // todo make this a smaller square or something
    // todo or maybe don't make it visible at all, and just an internal thing that's tracked.
    private static final ArrayList<Color> tableColors =
            new ArrayList<>(List.of(Color.AQUA, Color.WHITE));

    public Dishes(int currentTier) {
        super(currentTier, tableColors.size(), tableColors);
    }
}
