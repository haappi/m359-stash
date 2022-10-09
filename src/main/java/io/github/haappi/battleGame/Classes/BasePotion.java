package io.github.haappi.battleGame.Classes;

public class BasePotion {
    private final String name;
    private int baseHealDamageThing;
    private int level;
    private int manaCost;
    private int duration;
    private final int shopPrice;

    public BasePotion(PotionBuilder builder) {
        this.name = builder.name;
        this.baseHealDamageThing = builder.baseHealDamageThing;
        this.level = builder.level;
        this.manaCost = builder.manaCost;
        this.duration = builder.duration;
        this.shopPrice = builder.shopPrice;
    }

    public String getName() {
        return name;
    }

    public int getBaseHealDamageThing() {
        return baseHealDamageThing;
    }

    public void changeBaseHealDamageThing(int baseHealDamageThing) {
        this.baseHealDamageThing += baseHealDamageThing;
    }

    public int getLevel() {
        return level;
    }

    public void changeLevel(int level) {
        this.level += level;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void changeManaCost(int manaCost) {
        this.manaCost += manaCost;
    }

    public int getDuration() {
        return duration;
    }

    public void changeDuration(int duration) {
        this.duration += duration;
    }

    public static class PotionBuilder {
        private final String name;
        private int baseHealDamageThing;
        private int level;
        private int manaCost;
        private int duration;
        private int shopPrice;

        public PotionBuilder(String name) {
            this.name = name;
        }

        public PotionBuilder baseHealDamageThing(int baseHealDamageThing) {
            this.baseHealDamageThing = baseHealDamageThing;
            return this;
        }

        public PotionBuilder level(int level) {
            this.level = level;
            return this;
        }

        public PotionBuilder manaCost(int manaCost) {
            this.manaCost = manaCost;
            return this;
        }

        public PotionBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public PotionBuilder shopPrice(int shopPrice) {
            this.shopPrice = shopPrice;
            return this;
        }

        public BasePotion build() {
            return new BasePotion(this);
        }
    }
}
