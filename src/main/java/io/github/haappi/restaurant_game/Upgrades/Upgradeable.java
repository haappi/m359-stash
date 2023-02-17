package io.github.haappi.restaurant_game.Upgrades;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Upgradeable extends Region {
    private final int maxTier;
    private final ArrayList<Color> coloredTiers = new ArrayList<>();
    private int currentTier = 0;

    public Upgradeable(int currentTier, int maxTier, ArrayList<Color> coloredTiers) {
        this.currentTier = currentTier;
        this.maxTier = maxTier;
        this.coloredTiers.addAll(coloredTiers);

        if (coloredTiers.size() != maxTier) {
            throw new RuntimeException("Size of colored tiers doesn't match max tiers.");
        }
    }

    public Upgradeable(int maxTier, ArrayList<Color> coloredTiers) {
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
