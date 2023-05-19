package io.github.haappi;

import io.github.haappi.views.TaskTracker;
import java.util.ArrayList;
import java.util.HashMap;

public class Config {
  // singleton
  private static Config instance = null;
  private final HashMap<String, Object> config = new HashMap<>();

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
    return (String) config.get("displayName");
  }

  public void setDisplayName(String displayName) {
    config.put("displayName", displayName);
  }

  public String getIdToken() {
    return (String) config.get("idToken");
  }

  public void setIdToken(String idToken) {
    config.put("idToken", idToken);
  }

  public String setEmail(String email) {
    return (String) config.put("email", email);
  }

  public String getEmail() {
    return (String) config.get("email");
  }

  public ArrayList<TaskTracker.TaskObject> getTasks() {
    return (ArrayList<TaskTracker.TaskObject>) config.get("tasks");
  }

  public void setTasks(ArrayList<TaskTracker.TaskObject> tasks) {
    config.put("tasks", tasks);
  }

  public HashMap<String, Object> getConfig() {
    return config;
  }
}
