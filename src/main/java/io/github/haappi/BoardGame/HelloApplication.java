package io.github.haappi.BoardGame;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application {
    protected HashMap<String, String> config;
    protected JedisPool jedisPool;
    private static HelloApplication singleton;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private final Gson gsonInstance = new Gson();

    public static HelloApplication getInstance() {
        return singleton;
    }

    public Gson getGson() {
        return singleton.gsonInstance;
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

    @Override
    public void start(Stage stage) throws IOException {
        singleton = this;
        this.config = Utils.loadConfig();
        this.jedisPool = Utils.initJedis(this.config);
        // https://basri.dev/posts/2012-06-20-a-simple-jedis-publish-subscribe-example/
        final Jedis subscriberJedis = jedisPool.getResource();
        new Thread(() -> subscriberJedis.subscribe(Utils.getListener(), "test")).start();
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
