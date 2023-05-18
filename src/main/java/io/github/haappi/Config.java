package io.github.haappi;

import java.util.HashMap;

public class Config {
    // singleton
    private static Config instance = null;
    private final HashMap<String, String> config = new HashMap<>();

    private Config() {
        // private constructor
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getDisplayName() {
        return config.get("displayName");
    }

    public void setDisplayName(String displayName) {
        config.put("displayName", displayName);
    }

    public String getIdToken() {
        return config.get("idToken");
    }

    public void setIdToken(String idToken) {
        config.put("idToken", idToken);
    }

    public String setEmail(String email) {
        return config.put("email", email);
    }

    public String getEmail() {
        return config.get("email");
    }
}
