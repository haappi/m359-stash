package io.github.haappi.packets;

public enum Enums {
    SMALL("s"),
    MEDIUM("m"),
    LARGE("l"),

    BLUE("b"),
    YELLOW("y"),
    RED("r"),

    BOTTLE("b"),
    JUG("j"),
    GLASS("g");

    private final String string;

    Enums(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
