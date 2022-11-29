package io.github.haappi.BoardGame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import redis.clients.jedis.Jedis;

public class HelloController {
    @FXML private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Jedis instance = HelloApplication.getInstance().getResource();
        String json = HelloApplication.getInstance().getGson().toJson(new Test("heyyyy"));
        instance.publish("test", json);
        HelloApplication.getInstance().returnResource(instance);
    }
}
