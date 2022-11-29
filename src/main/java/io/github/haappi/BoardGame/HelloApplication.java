package io.github.haappi.BoardGame;

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
    @Override
    public void start(Stage stage) throws IOException {
        this.config = Utils.loadConfig();
        this.jedisPool = Utils.initJedis(this.config);
        // https://basri.dev/posts/2012-06-20-a-simple-jedis-publish-subscribe-example/
        final Jedis subscriberJedis = jedisPool.getResource();
        subscriberJedis.subscribe(Utils.getListener(), "test");
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
