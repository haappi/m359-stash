package io.github.haappi.battleGame.Classes;

import io.github.haappi.battleGame.Utils;

public class Weapons extends HoldableItem {
    private final int damage;
    private final int speed;
    private final int weight;
    private final Double price;
    private final String name;
    private final String description;
    private int durability;

    private Weapons(WeaponBuilder builder) {
        super(builder.name, builder.price);
        this.name = builder.name;
        this.price = builder.price;
        this.durability = builder.durability;
        this.damage = builder.damage;
        this.speed = builder.speed;
        this.weight = builder.weight;
        this.description = builder.description;
    }

    /**
     * Uses the weapon once. Reduces the durability by 1.
     *
     * @return A {@link Boolean} indicating whether the attack was successful or not.
     */
    public boolean useWeapon() {
        if (this.durability > 0) {
            this.durability--;
            return true;
        }
        return false;
    }

    public void handleCalc(Opponent opponent, Player player) {
        opponent.setHealth(opponent.getHealth() - Utils.reduceByFatigue(player.getAttack(), player.getFatigueLevel()));
    }

    public String toString() {
        return this.name + ": " + this.price;
    }

    public String getInformation() {
        return "Name: " + this.name + "\n" +
                "Price: " + this.price + "\n" +
                "Durability: " + this.durability + "\n" +
                "Damage: " + this.damage + "\n" +
                "Speed: " + this.speed + "\n" +
                "Weight: " + this.weight + "\n" +
                "Description: " + this.description + "\n";
    }

    @SuppressWarnings("unused")
    public static class WeaponBuilder {
        private final String name;
        private int durability;
        private int damage;
        private int speed;
        private int weight;
        private double price;
        private String description;

        public WeaponBuilder(String name) {
            this.name = name;
        }

        public WeaponBuilder(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public WeaponBuilder(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public WeaponBuilder setDurability(Integer durability) {
            this.durability = durability;
            return this;
        }

        public WeaponBuilder setDamage(Integer damage) {
            this.damage = damage;
            return this;
        }

        public WeaponBuilder setSpeed(Integer speed) {
            this.speed = speed;
            return this;
        }

        public WeaponBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public WeaponBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public WeaponBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Weapons build() {
            return new Weapons(this);
        }
    }
}
