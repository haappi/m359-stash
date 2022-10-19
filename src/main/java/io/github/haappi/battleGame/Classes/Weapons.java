package io.github.haappi.battleGame.Classes;

public class Weapons extends HoldableItem {
    private int durability;
    private int damage;
    private int range;
    private int speed;
    private int weight;
    private Double price;
    private String name;
    private String description;

    private Weapons(WeaponBuilder builder) {
        super(builder.name, builder.price);
        this.name = builder.name;
        this.price = builder.price;
        this.durability = builder.durability;
        this.damage = builder.damage;
        this.range = builder.range;
        this.speed = builder.speed;
        this.weight = builder.weight;
        this.description = builder.description;
    }

    public String toString() {
        return this.name + ": " + this.price;
    }

    @SuppressWarnings("unused")
    public static class WeaponBuilder {
        private final String name;
        private int durability;
        private int damage;
        private int range;
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

        public WeaponBuilder setRange(Integer range) {
            this.range = range;
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
