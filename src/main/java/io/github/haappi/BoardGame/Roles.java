package io.github.haappi.BoardGame;

public class Roles {
    private final String name;
    private final String description;

    public Roles(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
