package io.github.haappi.restaurant_game;

import javafx.scene.image.Image;

public class Party {
    private final int size;
    private final long creationTime = System.currentTimeMillis();
    private double currentReputation;
    private double currentHunger;
    private Image image; // depending on size, will be a different image

    public Party(int size, double currentReputation, double currentHunger, double currentThirst) {
        this.size = size;
        this.currentReputation = currentReputation;
        this.currentHunger = currentHunger;

        switch (size) {
            // set images
        }
    }

    public long getCreationTime() {
        return creationTime;
    }

    public int getSize() {
        return size;
    }

    public double getCurrentReputation() {
        return currentReputation;
    }

    public void setCurrentReputation(double currentReputation) {
        this.currentReputation = currentReputation;
    }

    public double getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(double currentHunger) {
        this.currentHunger = currentHunger;
    }
}
