package io.github.haappi.restaurant_game.Upgrades;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Chairs extends Upgradeable {
    private static final ArrayList<Color> chairColors =
            new ArrayList<>(
                    List.of(
                            Color.BROWN,
                            Color.ROSYBROWN,
                            Color.LIGHTCYAN,
                            Color.BEIGE,
                            Color.WHITE));

    public Chairs(int currentTier) {
        super(currentTier, chairColors.size(), chairColors);
    }
}
