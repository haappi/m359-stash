package io.github.haappi;

import com.gluonhq.charm.glisten.visual.Theme;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

//            List<NameValuePair> params = new ArrayList<>();
//            params.add(new BasicNameValuePair("configSetting", configSetting));
//            params.add(new BasicNameValuePair("configValue", configValue));
//
//            httpPatch.setEntity(new UrlEncodedFormEntity(params));

                String requestBody = String.format("{ \"%s\": \"%s\"}", configSetting, configValue);
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
//        });




    }
}
