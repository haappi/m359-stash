package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

public class ConfigFile {
    @Expose private final String extra = "";
    @Expose private String name;
    @Expose private String password;
    @Expose private String uri;

    public String getConnection() {
        return "mongodb+srv://" + name + ":" + password + "@" + uri + "" + extra;
    }

    @Override
    public String toString() {
        return this.getConnection();
    }
}
