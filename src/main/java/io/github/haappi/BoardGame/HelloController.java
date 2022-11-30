package io.github.haappi.BoardGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import redis.clients.jedis.Jedis;

public class HelloController {
    public TextField joinCode;
    @FXML private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Jedis instance = HelloApplication.getInstance().getResource();
        String json =
                HelloApplication.getInstance()
                        .getGson()
                        .toJson(new Test(HelloApplication.getInstance().getClientID()));
        Utils.p(instance, "test", json);
        new UserJoined("a").getClassType();
        HelloApplication.getInstance().returnResource(instance);
    }

    public void connectToGame(ActionEvent actionEvent) {
        Jedis instance = HelloApplication.getInstance().getResource();
        String json =
                HelloApplication.getInstance()
                        .getGson()
                        .toJson(new Test(HelloApplication.getInstance().getClientID()));
        Utils.p(instance, "test", json);
        HelloApplication.getInstance().returnResource(instance);
    }

    public void makeYourOwn(ActionEvent actionEvent) {
        return;
    }
}
