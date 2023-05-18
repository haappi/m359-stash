package io.github.haappi;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Storage {
    private static final HttpClient httpClient = HttpClients.createDefault();
    private static final String fireBaseURL = "https://java-final-project-b9dca-default-rtdb.firebaseio.com/";
    private static Storage instance = null;

    private Storage() {
        // Private constructor
    }

    public static Storage getHttpClient() {
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
}
