package io.github.haappi.battleGame.Classes;

import org.jetbrains.annotations.Nullable;

public class Potions extends HoldableItem {
    private final String statGiven;
    private final double amountGiven;
    private final String name;
    private final Double price;
    private final Double multiplier;


    public Potions(PotionBuilder potionBuilder) {
        super(potionBuilder.name, potionBuilder.price); // Super just calls the constructor of the class im extending from
        this.name = potionBuilder.name;
        this.price = potionBuilder.price;
        this.statGiven = potionBuilder.statGiven;
        this.amountGiven = potionBuilder.amountGiven;
        this.multiplier = potionBuilder.multiplier;
    }

    public String getStatGiven() {
        return statGiven;
    }

    public double getAmountGiven() {
        return amountGiven;
    }

    @Override
    public String getName() {
        return name;
    }

    public String toString() {
        return this.name + " " + this.amountGiven;
    }

    @Nullable
    @Override
    public Double getPrice() {
        return price;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public static class PotionBuilder {
        private final String name;
        private final Double price;
        private String statGiven;
        private double amountGiven;
        private Double multiplier;

        public PotionBuilder(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        public PotionBuilder setStatGiven(String statGiven) {
            this.statGiven = statGiven;
            return this;
        }

        public PotionBuilder setAmountGiven(double amountGiven) {
            this.amountGiven = amountGiven;
            return this;
        }

        public PotionBuilder setMultiplier(Double multiplier) {
            this.multiplier = multiplier;
            return this;
        }

        public Potions build() {
            return new Potions(this);
        }
    }

}
