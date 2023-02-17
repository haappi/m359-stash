package io.github.haappi.restaurant_game;

import io.github.haappi.restaurant_game.Tiles.TableTile;

public class Party {
    private final int size;
    private final long creationTime = System.currentTimeMillis();
    private final String emoji; // depending on size, will be a different image
    private long lastUpdate = creationTime;
    private double currentHappiness = 10.0;
    private double currentReputation = 2.0;
    private int currentX;
    private int currentY;

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public TableTile getSeatedAt() {
        return seatedAt;
    }

    public void setSeatedAt(TableTile seatedAt) {
        this.seatedAt = seatedAt;
    }

    private TableTile seatedAt;

    public Party(int size) {
        this(size, 2.0, 10.0);
    }

    public Party(int size, double currentReputation, double currentHunger) {
        this.size = size;
        this.currentReputation = currentReputation;
        this.currentHappiness = currentHunger;

        switch (size) {
            case 1 -> this.emoji = "\uD83E\uDD35"; // solo tux emoji
            case 2 -> this.emoji =
                    "\uD83E\uDDD1\u200D\uD83E\uDD1D\u200D\uD83E\uDDD1"; // couple emoji
            case 3 -> this.emoji = "\uD83D\uDC6A"; // trio family emoji
            case 4 -> this.emoji =
                    "\uD83D\uDC69\u200D\uD83D\uDC69\u200D\uD83D\uDC66\u200D\uD83D\uDC66"; // quad
                // family
                // emoji
            default -> this.emoji =
                    "\uD83D\uDC7B"; // what the hell happened to them lmao // a ghost
        }
    }

    public int getSize() {
        return size;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        if (lastUpdate - this.lastUpdate > 5000) {
            // if so, update the happiness and reputation
            currentHappiness -= 0.5 * (lastUpdate - this.lastUpdate) / 1000;
            currentReputation -= 0.5 * (lastUpdate - this.lastUpdate) / 1000;
        }

        if (currentHappiness < 5.0 || currentReputation < 0.5) {
            // if so, they leave
            seatedAt.angryParty(this);
            System.out.println("Party of size " + size + " left due to bad service");
            return;
        }

        this.lastUpdate = lastUpdate;
    }

    public double getCurrentHappiness() {
        return currentHappiness;
    }

    public void setCurrentHappiness(double currentHappiness) {
        this.currentHappiness = currentHappiness;
    }

    public double getCurrentReputation() {
        return currentReputation;
    }

    public void setCurrentReputation(double currentReputation) {
        this.currentReputation = currentReputation;
    }

    public String toString() {
        return getEmoji();
    }

    public String getEmoji() {
        return emoji;
    }

    public Double getPossibleRating() {
        return (currentReputation + currentHappiness) / 2;
    }
}
