package io.github.haappi;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GlobalHttpClass {
    private static final HttpClient httpClient = HttpClients.createDefault();
    private static final String fireBaseURL = "https://identitytoolkit.googleapis.com/v1/";
    private static GlobalHttpClass instance = null;

    private GlobalHttpClass() {
        // Private constructor
    }

    public static GlobalHttpClass getHttpClient() {
        if (instance == null) {
            instance = new GlobalHttpClass();
        }
        return instance;
    }

    public HttpClient getHttpClientInstance() {
        return httpClient;
    }


    public Response login(String username, String password) throws IOException {
        HttpPost httpPost = new HttpPost(fireBaseURL + "accounts:signInWithPassword");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("key", HelloApplication.properties.getProperty("apiKey")));
        params.add(new BasicNameValuePair("email", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("returnSecureToken", "true"));


        httpPost.setEntity(new UrlEncodedFormEntity(params));

        String resp = httpClient.execute(httpPost, response -> {
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

        if (resp == null) {
            return new Response(false, "Error: Username or password incorrect");
        }

        JSONObject json = new JSONObject(resp);
        System.out.println(json.toString(4));

        Config.getInstance().setDisplayName(!Objects.equals(json.getString("displayName"), "") ? json.getString("displayName") : json.getString("email"));
        Config.getInstance().setIdToken(json.getString("idToken"));
        Config.getInstance().setEmail(json.getString("email"));

        return new Response(true, "Login successful: Logged in as " + Config.getInstance().getDisplayName());
    }
}
