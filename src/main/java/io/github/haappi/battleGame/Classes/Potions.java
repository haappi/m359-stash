package io.github.haappi.battleGame.Classes;

public class Potions extends HoldableItem {
    private final boolean throwable;
    private final double health;


    public Potions(String name, Double price, boolean throwable, double health) {
        super(name, price); // Super just calls the constructor of the class im extending from
        this.throwable = throwable;
        this.health = health;
    }

}
