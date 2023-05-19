package io.github.haappi;

import io.github.haappi.views.TaskTracker;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.haappi.Utils.jsonString;

public class Storage {
    private static final HttpClient httpClient = HttpClients.createDefault();
    private static final String fireBaseURL = "https://java-final-project-b9dca-default-rtdb.firebaseio.com/";
    private static Storage instance = null;

    private Storage() {
        // Private constructor
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public HttpClient getHttpClientInstance() {
        return httpClient;
    }

    public <E> void saveData(String username, String path, List<E> data) throws IOException {
        username = username.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + username + "/" + path + ".json";

        HttpPatch httpPatch = new HttpPatch(url);

        httpPatch.setEntity(new UrlEncodedFormEntity((List<? extends NameValuePair>) data));

        String resp = httpClient.execute(httpPatch, response -> {
            if (response.getCode() >= 300) {
                return null;
            }
            final HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
                return null;
            }
            try (InputStream inputStream = responseEntity.getContent()) {
                return new String(inputStream.readAllBytes());
            }
        });

        JSONObject json = new JSONObject(resp);
        System.out.println(json.toString(4));
    }

    public HashMap<String, String> loadConfig(String username) throws IOException {
        username = username.split("@")[0]; // Remove domain (if any)
        username = username.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + username + "/" + "config" + ".json";
        HttpGet httpGet = new HttpGet(url);

        String resp = httpClient.execute(httpGet, response -> {
            if (response.getCode() >= 300) {
                return null;
            }
            final HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
                return null;
            }
            try (InputStream inputStream = responseEntity.getContent()) {
                return new String(inputStream.readAllBytes());
            }
        });

        if (resp == null || resp.equals("null")) {
            return null;
        }
        System.out.println(resp);

        JSONObject json = new JSONObject(resp);
        HashMap<String, String> config = new HashMap<>();
        config.put("theme", json.getString("theme"));
        config.put("darkModeEnabled", json.getString("darkModeEnabled"));
        // somehow force apply it
        return config;
    }

    public void setConfigValue(String username, String configSetting, String configValue) throws IOException {
        Config.getInstance().getConfig().put(configSetting, configValue);
        System.out.println("ha loser");

//        new Thread(() -> {
        String uusername = username.split("@")[0]; // Remove domain (if any)
        uusername = uusername.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + uusername + "/" + "config" + ".json";

        System.out.println(url);

        HttpPatch httpPatch = new HttpPatch(url);

        httpPatch.setEntity(new StringEntity(jsonString(Map.of(configSetting, configValue)), ContentType.APPLICATION_JSON));


        String resp = null;
        try {
            resp = httpClient.execute(httpPatch, response -> {
                System.out.println(response.getCode());
                if (response.getCode() >= 300) {
                    return null;
                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                try (InputStream inputStream = responseEntity.getContent()) {
                    return new String(inputStream.readAllBytes());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resp == null || resp.equals("null")) {
            return;
        }

        JSONObject json = new JSONObject(resp);
        System.out.println(json.toString(4));
//        });


    }


    @Deprecated
    public String getPreviousTasks(String username) {
        username = username.split("@")[0]; // Remove domain (if any)
        username = username.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + username + "/" + "tasks" + ".json";
        HttpGet httpGet = new HttpGet(url);

        String resp = null;
        try {
            resp = httpClient.execute(httpGet, response -> {
                if (response.getCode() >= 300) {
                    return null;
                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                try (InputStream inputStream = responseEntity.getContent()) {
                    return new String(inputStream.readAllBytes());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resp == null || resp.equals("null")) {
            return null;
        }
        System.out.println(resp);

        JSONObject json = new JSONObject(resp);
        StringBuilder sb = new StringBuilder();
        for (String key : json.keySet()) {
            sb.append(key).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<TaskTracker.TaskObject> getTasks() {
        String username = Config.getInstance().getDisplayName();

        username = username.split("@")[0]; // Remove domain (if any)
        username = username.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + username + ".json";
        HttpGet httpGet = new HttpGet(url);

        String resp = null;
        try {
            resp = httpClient.execute(httpGet, response -> {
                if (response.getCode() >= 300) {
                    return null;
                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                try (InputStream inputStream = responseEntity.getContent()) {
                    return new String(inputStream.readAllBytes());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resp == null || resp.equals("null")) {
            return new ArrayList<>();
        }

        JSONObject json = new JSONObject(resp);
        ArrayList<TaskTracker.TaskObject> tasks = new ArrayList<>();

        System.out.println(json.toString(4));

        for (String key : json.keySet()) {
            JSONArray arr = json.getJSONArray(key);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                tasks.add(new TaskTracker.TaskObject(obj.getString("name"), obj.getString("date")));
            }
        }

        return tasks;
    }

    public void appendTask(TaskTracker.TaskObject taskObject) {
        ArrayList<TaskTracker.TaskObject> tasks = getTasks();
        tasks.add(taskObject);
        setTasks(Config.getInstance().getDisplayName(), tasks);
    }

    private void setTasks(String username, ArrayList<TaskTracker.TaskObject> tasks) {
        username = username.split("@")[0]; // Remove domain (if any)
        username = username.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + username + ".json";

        HttpPatch httpPatch = new HttpPatch(url);

        String json = HelloApplication.gson.toJson(Map.of("tasks", tasks));
        System.out.println("json string: " + json);

        httpPatch.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        String resp = null;

        try {
            resp = httpClient.execute(httpPatch, response -> {
                System.out.println(response.getCode());
//                if (response.getCode() >= 300) {
//                    return null;
//                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                try (InputStream inputStream = responseEntity.getContent()) {
                    return new String(inputStream.readAllBytes());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resp == null || resp.equals("null")) {
            return;
        }

        System.out.println(resp);

        Config.getInstance().setTasks(tasks);
    }

    public void removeTask(TaskTracker.TaskObject taskObject) {
        ArrayList<TaskTracker.TaskObject> tasks = getTasks();
        tasks.remove(taskObject);
        setTasks(Config.getInstance().getDisplayName(), tasks);
    }

    @Deprecated
    public void addTask(String username, String task) {
        // get old tasks and append it
        String oldTasks = getPreviousTasks(username);
        if (oldTasks == null) {
            oldTasks = "";
        }
        oldTasks += task + "\n";

        username = username.split("@")[0]; // Remove domain (if any)
        username = username.replaceAll("[^A-Za-z0-9]", "");
        String url = fireBaseURL + "users/" + username + "/" + "tasks" + ".json";

        HttpPatch httpPatch = new HttpPatch(url);

        String requestBody = String.format("{ \"%s\": \"%s\"}", "tasks", oldTasks);
        System.out.println(requestBody);
        httpPatch.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

        String resp = null;

        try {
            resp = httpClient.execute(httpPatch, response -> {
                System.out.println(response.getCode());
                if (response.getCode() >= 300) {
                    return null;
                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                try (InputStream inputStream = responseEntity.getContent()) {
                    return new String(inputStream.readAllBytes());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resp == null || resp.equals("null")) {
            return;
        }

        JSONObject json = new JSONObject(resp);
        System.out.println(json.toString(4));
    }

    @Deprecated
    public void removeTask(String username, String taskObj) {
        // get old tasks and append it
        String oldTasks = getPreviousTasks(username);
        if (oldTasks == null) {
            oldTasks = "";
        }
        oldTasks = oldTasks.replace(taskObj + "\n", "");

        username = username.split("@")[0]; // Remove domain (if any)
        username = username.replaceAll("[^A-Za-z0-9]", "");

        String url = fireBaseURL + "users/" + username + "/" + "tasks" + ".json";

        HttpPatch httpPatch = new HttpPatch(url);

        String requestBody = String.format("{ \"%s\": \"%s\"}", "tasks", oldTasks);
        httpPatch.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

        String resp = null;

        try {
            resp = httpClient.execute(httpPatch, response -> {
                System.out.println(response.getCode());
                if (response.getCode() >= 300) {
                    return null;
                }
                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity == null) {
                    return null;
                }
                try (InputStream inputStream = responseEntity.getContent()) {
                    return new String(inputStream.readAllBytes());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resp == null || resp.equals("null")) {
            return;
        }

        JSONObject json = new JSONObject(resp);
        System.out.println(json.toString(4));

    }
}
