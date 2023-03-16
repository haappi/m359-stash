package io.github.haappi.shared;

public enum Enums {
    // Size
    LARGE("large"),
    MEDIUM("medium"),
    SMALL("small"),

    // Color
    BLUE("blue"),
    ORANGE("orange"),
    YELLOW("yellow"),

    // Container
    BOTTLE("bottle"),
    CUP("cup"),
    JAR("jar"),

    // Pattern
    DOTS("dots"),
    STARS("stars"),
    STRIPES("stripes"),

    BACK_CARD("back");

    private final String string;

    Enums(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
