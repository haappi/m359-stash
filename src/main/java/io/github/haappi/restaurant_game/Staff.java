package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;
import io.github.haappi.restaurant_game.PathFinding.AStar;
import io.github.haappi.restaurant_game.PathFinding.Node;
import io.github.haappi.restaurant_game.Tiles.TableTile;
import io.github.haappi.restaurant_game.Tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static io.github.haappi.restaurant_game.Utils.getRandomNumber;

public class Staff {
    @Expose
    private final Building building;
    @Expose
    private final String name = Utils.stringGenerator(5);
    private final Text text = new Text();
    @Expose
    private int tier = 1;
    private int currentX = 0;
    private int currentY = 0;
    private Party party;
    @Expose
    private int salary = 10;
    @Expose
    private int happiness = 100;
    private ArrayList<Node> currentPathToFollow = new ArrayList<>();
    @Expose
    private long lastTimeSalaryIncreased = System.currentTimeMillis();

    public Staff(Building building) {
        this.building = building;
        text.setText(toString());
    }

    public String getName() {
        return name;
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

    public ArrayList<Node> getCurrentPathToFollow() {
        return currentPathToFollow;
    }

    public void setCurrentPathToFollow(ArrayList<Node> currentPathToFollow) {
        this.currentPathToFollow = currentPathToFollow;
    }

    public void moveToGoal(int x, int y) {
        currentPathToFollow.clear();
        AStar aStar = new AStar(building.getTiles(), x, y);
        aStar.calculate(currentX, currentY);
        currentPathToFollow.addAll(aStar.getPath());
    }

    public void move() {
        System.out.println("Moving staff");
        Tile[][] board = new Tile[0][];
        if (currentPathToFollow.size() == 0) {
            board = building.getTiles();
            if (board[currentX - 1][currentY - 1] instanceof TableTile tiled) {
                tiled.setOccupyingParty(null);
            } else {
                Game game = HelloApplication.getInstance().getGameInstance();
                Party party1 = getParty();
                if (party1 == null) {
                    return;
                }
                moveToGoal(party1.getCurrentX(), party1.getCurrentY());
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

    public String toString() {
        return "\uD83E\uDDD1\u200D\uD83C\uDF73"; // chef emoji
    }

    public boolean tryTraining() {
        if (tier > 5) {
            return false;
        }

        if (building.getMoney() >= 100) {
            building.addOrRemoveMoney(-100);
            if (getRandomNumber(1, 10) > 6) {
                tier++;
                salary += 10 * tier * getRandomNumber(1, 2);
                happiness = 100;
                return true;
            }
        }
        happiness -= 10 * (5 - tier);
        return false;
    }

    public boolean wantsToLeave() {
        return happiness < 40
                || System.currentTimeMillis() - lastTimeSalaryIncreased > 300_000; // 5 minutes
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public long getLastTimeSalaryIncreased() {
        return lastTimeSalaryIncreased;
    }

    public void setLastTimeSalaryIncreased(long lastTimeSalaryIncreased) {
        this.lastTimeSalaryIncreased = lastTimeSalaryIncreased;
    }

    public Building getBuilding() {
        return building;
    }

    public String staffInformation() {
        return "Name: " + name + " | Tier: " + tier + " | Salary: " + salary + " | Happiness: " + happiness;
    }

    public void setPartyHelping(Party party) {
        this.party = party;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public void doTask() {
        AnimationTimer timer;
        Staff staff = this;
        timer = new AnimationTimer() {
            long lastTimerUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastTimerUpdate > 1_000_000_000) {
                    lastTimerUpdate = now;
                    staff.move();
                    if (staff.getParty() == null) {
                        this.stop();
                    }
                }


            }
        };
        timer.start();
    }
}
