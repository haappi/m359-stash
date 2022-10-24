package io.github.haappi.battleGame.Classes;

import io.github.haappi.battleGame.InventoryItem;
import io.github.haappi.battleGame.Utils;

import java.util.ArrayList;

public class Opponent {
    private final String name;
    private final String type;
    private final ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private final double maxHealth;
    private final double attack;
    private final double defense;
    private final int speed;
    private final int luck;
    private final double fatigueLevel;
    private double health;
    private HoldableItem heldItem;

    public Opponent(OpponentBuilder builder) {
        this.name = builder.name;
        this.type = builder.type;
        double additionalMulti = Utils.getAddtionalMultiplier(this.type);
        this.health = builder.maxHealth * additionalMulti;
        this.maxHealth = builder.maxHealth * additionalMulti;
        this.attack = builder.attack * additionalMulti;
        this.defense = builder.defense * additionalMulti;
        this.speed = builder.speed * (int) additionalMulti;
        this.fatigueLevel = 0;
        this.luck = builder.luck;
    }

    public Opponent(String data) {
        String[] splitData = data.split(";");
        this.name = splitData[0];
        this.type = splitData[1];
        this.health = Double.parseDouble(splitData[2]);
        this.maxHealth = Double.parseDouble(splitData[3]);
        this.attack = Double.parseDouble(splitData[4]);
        this.defense = Double.parseDouble(splitData[5]);
        this.speed = Integer.parseInt(splitData[6]);
        this.luck = Integer.parseInt(splitData[7]);
        this.fatigueLevel = Double.parseDouble(splitData[8]);
    }

    public HoldableItem getHeldItem() {
        return heldItem;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLuck() {
        return luck;
    }

    public double getFatigueLevel() {
        return fatigueLevel;
    }

    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public static class OpponentBuilder {
        private final String name;
        private final String type;
        private double maxHealth;
        private double attack;
        private double defense;
        private int speed;
        private int luck;

        public OpponentBuilder(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public OpponentBuilder setHealth(double health) {
            this.maxHealth = maxHealth;
            return this;
        }

        public OpponentBuilder setAttack(double attack) {
            this.attack = attack;
            return this;
        }

        public OpponentBuilder setDefense(double defense) {
            this.defense = defense;
            return this;
        }

        public OpponentBuilder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public OpponentBuilder setLuck(int luck) {
            this.luck = luck;
            return this;
        }

        public Opponent build() {
            return new Opponent(this);
        }
    }
}
