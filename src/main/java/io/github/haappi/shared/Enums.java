package io.github.haappi.shared;

public enum Enums {
    LARGE("large"),
    MEDIUM("medium"),
    SMALL("small"),

    BLUE("blue"),
    ORANGE("orange"),
    YELLOW("yellow"),

    BOTTLE("bottle"),
    CUP("cup"),
    JAR("jar"),

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
