package io.github.haappi.battleGame.Classes;

import io.github.haappi.battleGame.InventoryItem;

import java.util.ArrayList;

public class Player {
    private final ArrayList<InventoryItem> inventory = new ArrayList<>();
    private String name;
    private double currentHealth;
    private double maxHealth;
    private double attack;
    private double defense;
    private int speed;
    private int currentMana;
    private int maxMana;
    private int luck;
    private double fatigueLevel;
    private final String clazz;

    public Player(PlayerBuilder builder) {
        this.name = builder.name;
        this.currentHealth = builder.maxHealth;
        this.maxHealth = builder.maxHealth;
        this.attack = builder.attack;
        this.defense = builder.defense;
        this.speed = builder.speed;
        this.currentMana = builder.maxMana;
        this.maxMana = builder.maxMana;
        this.fatigueLevel = builder.fatigueLevel;
        this.luck = builder.luck;
        this.clazz = builder.clazz;
    }

    public Player(String name, double currentHealth, double maxHealth, double attack, double defense, int speed, int maxMana, double fatigueLevel, String clazz) {
        this.name = name;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxMana = maxMana;
        this.fatigueLevel = fatigueLevel;
        this.clazz = clazz;
    }

    public String getPlayerDataAsString() {
        return "Name: " + name + "\n" +
                "Health: " + currentHealth + "/" + maxHealth + "\n" +
                "Attack: " + attack + "\n" +
                "Defense: " + defense + "\n" +
                "Speed: " + speed + "\n" +
                "Mana: " + currentMana + "/" + maxMana + "\n" +
                "Fatigue: " + fatigueLevel + "\n" +
                "Class: " + clazz + "\n";
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public Player(String data) {
        String[] splitData = data.split(";");
        this.name = splitData[0];
        this.currentHealth = Double.parseDouble(splitData[1]);
        this.maxHealth = Double.parseDouble(splitData[2]);
        this.attack = Double.parseDouble(splitData[3]);
        this.defense = Double.parseDouble(splitData[4]);
        this.speed = Integer.parseInt(splitData[5]);
        this.currentMana = Integer.parseInt(splitData[6]);
        this.maxMana = Integer.parseInt(splitData[7]);
        this.fatigueLevel = Double.parseDouble(splitData[8]);
        this.clazz = splitData[9];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public double getFatigueLevel() {
        return fatigueLevel;
    }

    public void setFatigueLevel(int fatigueLevel) {
        this.fatigueLevel = fatigueLevel;
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public static class PlayerBuilder {
        private final String name;
        private double maxHealth;
        private double attack;
        private double defense;
        private int speed;
        private int maxMana;
        private int fatigueLevel;
        private int luck;
        private String clazz;

        public PlayerBuilder setClazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public PlayerBuilder(String name) {
            this.name = name;
        }

        public PlayerBuilder setMaxHealth(double maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public PlayerBuilder setLuck(int luck) {
            this.luck = luck;
            return this;
        }

        public PlayerBuilder setAttack(double attack) {
            this.attack = attack;
            return this;
        }

        public PlayerBuilder setDefense(double defense) {
            this.defense = defense;
            return this;
        }

        public PlayerBuilder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public PlayerBuilder setMaxMana(int maxMana) {
            this.maxMana = maxMana;
            return this;
        }

        public PlayerBuilder setFatigueLevel(int fatigueLevel) {
            this.fatigueLevel = fatigueLevel;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }


}
