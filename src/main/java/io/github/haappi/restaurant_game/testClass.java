package io.github.haappi.restaurant_game;

public class testClass {
    private final String name;
    private final long timeCreated = System.currentTimeMillis();

    public testClass(String name) {
        this.name = name;
    }

    public String toString() {
        return "{testClass, name=" + name + ", timeCreated=" + timeCreated + "}";
    }
}
