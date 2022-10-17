package io.github.haappi.battleGame.Classes;

public class Weapons extends HoldableItem{
    private int durability;
    private int damage;
    private int range;
    private int speed;
    private int weight;
    private int value;
    private String name;
    private String description;

    public Weapons(String name, int value, int durability, int damage, int range, int speed, int weight, String description) {
        super(name, value);
        this.durability = durability;
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.weight = weight;
        this.description = description;
    }
    public Weapons(String name) {
        super(name, 1);
    }

    public String toString() {
        return this.name;
    }
}
