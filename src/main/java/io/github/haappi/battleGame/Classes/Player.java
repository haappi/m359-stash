package io.github.haappi.battleGame.Classes;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final ArrayList<Objects> inventory = new ArrayList<>();
    private String name;
    private int currentHealth;
    private int maxHealth;
    private int attack;
    private int defense;
    private int speed;
    private int currentMana;
    private int maxMana;
    private int fatigueLevel;

    public Player(PlayerBuilder builder) {
        this.name = builder.name;
        this.currentHealth = builder.currentHealth;
        this.maxHealth = builder.maxHealth;
        this.attack = builder.attack;
        this.defense = builder.defense;
        this.speed = builder.speed;
        this.currentMana = builder.currentMana;
        this.maxMana = builder.maxMana;
        this.fatigueLevel = builder.fatigueLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
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

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public void setFatigueLevel(int fatigueLevel) {
        this.fatigueLevel = fatigueLevel;
    }

    public ArrayList<Objects> getInventory() {
        return inventory;
    }

    public static class PlayerBuilder {
        private final String name;
        private int currentHealth;
        private int maxHealth;
        private int attack;
        private int defense;
        private int speed;
        private int currentMana;
        private int maxMana;
        private int fatigueLevel;

        public PlayerBuilder(String name) {
            this.name = name;
        }

        public PlayerBuilder setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
            return this;
        }

        public PlayerBuilder setMaxHealth(int maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public PlayerBuilder setAttack(int attack) {
            this.attack = attack;
            return this;
        }

        public PlayerBuilder setDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public PlayerBuilder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public PlayerBuilder setCurrentMana(int currentMana) {
            this.currentMana = currentMana;
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
