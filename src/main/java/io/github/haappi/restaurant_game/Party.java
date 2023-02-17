package io.github.haappi.restaurant_game;

public class Party {
    private final int size;
    private final long creationTime = System.currentTimeMillis();
    private final String emoji; // depending on size, will be a different image
    private long lastUpdate = creationTime;
    private double currentHappiness = 10.0;
    private double currentReputation = 2.0;

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
