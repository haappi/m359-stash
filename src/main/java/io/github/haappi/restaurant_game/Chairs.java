package io.github.haappi.restaurant_game;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Chairs extends Upgrades {
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
