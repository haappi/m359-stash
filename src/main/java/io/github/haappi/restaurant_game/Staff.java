package io.github.haappi.restaurant_game;

import static io.github.haappi.restaurant_game.Utils.getRandomNumber;

import com.google.gson.annotations.Expose;

public class Staff {
    @Expose private final Building building;
    @Expose private int tier = 1;
    @Expose private int salary = 10;
    @Expose private int happiness = 100;
    @Expose private long lastTimeSalaryIncreased = System.currentTimeMillis();

    public Staff(Building building) {
        this.building = building;
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

    public int getSalary() {
        return salary;
    }

    public int getHappiness() {
        return happiness;
    }

    public long getLastTimeSalaryIncreased() {
        return lastTimeSalaryIncreased;
    }

    public Building getBuilding() {
        return building;
    }
}
