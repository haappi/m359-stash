package io.github.haappi.battleGame.Classes;

public class Weapons extends HoldableItem {
    private int durability;
    private int damage;
    private int range;
    private int speed;
    private int weight;
    private int value;
    private Double price;
    private String name;
    private String description;

    public Weapons(String name, Double price, int durability, int damage, int range, int speed, int weight, String description) {
        super(name, price);
        this.durability = durability;
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.weight = weight;
        this.description = description;
    }

    public Weapons(String name, Double price) {
        super(name, price);
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return this.name + ": " + this.price;
    }
}
