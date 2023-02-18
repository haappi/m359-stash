package io.github.haappi.restaurant_game;

import io.github.haappi.restaurant_game.PathFinding.AStar;
import io.github.haappi.restaurant_game.PathFinding.Node;
import io.github.haappi.restaurant_game.Tiles.TableTile;
import io.github.haappi.restaurant_game.Tiles.Tile;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Party {
    private final int size;
    private final long creationTime = System.currentTimeMillis();
    private final String emoji; // depending on size, will be a different image
    private final ArrayList<Node> currentPathToFollow = new ArrayList<>();
    private final Tile[][] board;
    private final Text text = new Text();
    private long lastUpdate = creationTime;
    private double currentHappiness = 10.0;
    private double currentReputation = 2.0;
    private int currentX;
    private int currentY;
    private TableTile seatedAt;
    private Staff waiter;

    public Party(int size, Tile[][] board) {
        this(size, 2.0, 10.0, board);
    }

    public Party(int size, double currentReputation, double currentHunger, Tile[][] board) {
        currentX = 7;
        currentY = 7;

        this.size = size;
        this.currentReputation = currentReputation;
        this.currentHappiness = currentHunger;
        this.board = board;

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
        text.setText(emoji);

        Platform.runLater(() -> board[currentX][currentY].getChildreen().add(text));

        AnimationTimer timer;
        Party party = this;
        timer =
                new AnimationTimer() {
                    long lastTimerUpdate = 0;

                    @Override
                    public void handle(long now) {
                        if (now - lastTimerUpdate > 1_000_000_000) {
                            lastTimerUpdate = now;
                            party.move();
                            if (party.getSeatedAt() != null) {
                                this.stop();
                            }
                        }
                    }
                };
        timer.start();
    }

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

    public void moveToGoal(int x, int y) {
        currentPathToFollow.clear();
        AStar aStar = new AStar(board, x, y);
        aStar.calculate(currentX, currentY);
        currentPathToFollow.addAll(aStar.getPath());
    }

    public void move() {
        System.out.println("Moving party of size " + size);
        if (currentPathToFollow.size() == 0) {
            if (board[currentX - 1][currentY - 1] instanceof TableTile) {
                setSeatedAt((TableTile) board[currentX - 1][currentY - 1]);
                Staff waiter =
                        HelloApplication.getInstance()
                                .getGameInstance()
                                .getCurrentBuilding()
                                .getClosestWaiter(currentX, currentY);
                if (waiter == null) {
                    Building building =
                            HelloApplication.getInstance().getGameInstance().getCurrentBuilding();
                    building.setRating(building.getRating() - .5 * this.getSize());
                    HelloApplication.getInstance()
                            .getGameInstance()
                            .setMoney(
                                    HelloApplication.getInstance().getGameInstance().getMoney()
                                            - 10 * this.getSize());
                    System.out.println("Party of size " + size + " left due to no waiters");
                    return;
                }
                this.waiter = waiter;
                waiter.setPartyHelping(this);
                waiter.doTask();
            } else {
                Game game = HelloApplication.getInstance().getGameInstance();
                TableTile tile = game.getCurrentBuilding().closestEmptyTable();
                if (tile == null) {
                    Building building = game.getCurrentBuilding();
                    building.setRating(building.getRating() - .5 * this.getSize());
                    game.setMoney(game.getMoney() - 10 * this.getSize());
                    System.out.println("Party of size " + size + " left due to no empty tables");
                    return;
                }
                moveToGoal(tile.getX(), tile.getY());
            }
            return;
        }
        Node nextPosition = currentPathToFollow.get(0);
        board[currentX][currentY].getChildreen().remove(text);
        currentX = nextPosition.getX();
        currentY = nextPosition.getY();
        board[currentX][currentY].getChildreen().add(text);
        currentPathToFollow.remove(0);
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
