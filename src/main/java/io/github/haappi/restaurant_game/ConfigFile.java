package io.github.haappi.restaurant_game;

public class ConfigFile {
    private final String extra = "";
    private String name;
    private String password;
    private String uri;

    public String getConnection() {
        return "mongodb+srv://" + name + ":" + password + "@" + uri + "" + extra;
    }

    @Override
    public String toString() {
        return this.getConnection();
    }
}
