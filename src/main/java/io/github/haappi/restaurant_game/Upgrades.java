package io.github.haappi.restaurant_game;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Upgrades extends Button {
    private final int maxTier;
    private final ArrayList<Color> coloredTiers = new ArrayList<>();
    private int currentTier = 0;

    public Upgrades(int currentTier, int maxTier, ArrayList<Color> coloredTiers) {
        this.currentTier = currentTier;
        this.maxTier = maxTier;
        this.coloredTiers.addAll(coloredTiers);

        if (coloredTiers.size() != maxTier) {
            throw new RuntimeException("Size of colored tiers doesn't match max tiers.");
        }
    }

    public Upgrades(int maxTier, ArrayList<Color> coloredTiers) {
        this(0, maxTier, coloredTiers);
    }

    public int getCurrentTier() {
        return currentTier;
    }

    public int incrementTier() {
        if (currentTier < maxTier) {
            currentTier++;
        }
        return currentTier;
    }

    public int getMaxTier() {
        return maxTier;
    }

    public ArrayList<Color> getColoredTiers() {
        return coloredTiers;
    }

    public Color getCurrentTierColor() {
        return coloredTiers.get(currentTier);
    }
}
