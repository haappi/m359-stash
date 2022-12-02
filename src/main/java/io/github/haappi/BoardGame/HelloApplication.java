package io.github.haappi.BoardGame;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class HelloApplication extends Application {
    public static final int WIDTH = 1880;
    public static final int HEIGHT = 1040;
    private static HelloApplication singleton;
    private final ArrayList<Thread> threads = new ArrayList<>();
    private final Gson gsonInstance = new Gson();
    private Stage stage;
    protected HashMap<String, String> config;
    protected JedisPool jedisPool;
    private String clientID;
    private String lobbyCode;

    public static HelloApplication getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        launch();
    }

    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }

    public Gson getGson() {
        return singleton.gsonInstance;
    }

    public String getClientID() {
        return this.clientID;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setScene(String fileName, boolean fullScreen) throws IOException {
        fileName = fileName.replace(".fxml", "") + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(fullScreen);
    }

    public void setScene(String fileName) throws IOException {
        setScene(fileName, true);
    }

    public Jedis getResource() {
        return HelloApplication.singleton.jedisPool.getResource();
    }

    public Long getBorrowedCount() {
        return singleton.jedisPool.getCreatedCount();
    }

    public void returnResource(Jedis instance) {
        singleton.jedisPool.returnResource(instance);
    }

    public void addThread(Thread thread) {
        this.threads.add(thread);
    }

    @Override
    public void start(Stage stage) throws IOException {
        singleton = this;
        this.config = Utils.loadConfig();
        this.jedisPool = Utils.initJedis(this.config);
        try {
            UUID.fromString(this.config.get("CLIENT-ID"));
        } catch (IllegalArgumentException e) {
            Platform.exit();
            throw new RuntimeException("Malformed UUID. Received: " + config.get("CLIENT-ID"));
        }
        this.clientID = this.config.get("CLIENT-ID");
        // https://basri.dev/posts/2012-06-20-a-simple-jedis-publish-subscribe-example/
        this.stage = stage;
        setScene("hello-view", true);
        stage.setOnCloseRequest(
                event -> {
                    if (this.lobbyCode != null && !this.lobbyCode.isEmpty()) {
                        Utils.p(new UserLeft());
                    }
                    threads.forEach(Thread::interrupt); // somehow close all the threads (this isn't
                    // working)
                });
    }
}
