package io.github.haappi.restaurant_game;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Tables extends Upgrades {
    private final static ArrayList<Color> tableColors = new ArrayList<>(List.of(Color.AQUA, Color.WHITE));

    public Tables(int currentTier) {
        super(currentTier, tableColors.size(), tableColors);
    }
}
