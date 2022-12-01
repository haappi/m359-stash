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
        if (joinCode.getText() == null || joinCode.getText().isEmpty()) {
            return;
        }
        HelloApplication.getInstance().setLobbyCode(joinCode.getText().toLowerCase());

        //        Jedis instance = HelloApplication.getInstance().getResource();
        /* todo
        some sort of key mapping persistently stored within redis (that gets deleted when application dies or after an hour or so)
        that maps a key to a lobby code
        player count
        if game started
        maybe store player things in key mapping?
         */
        Utils.p(new Test(HelloApplication.getInstance().getClientID()));
        final Jedis subscriberJedis = HelloApplication.getInstance().getResource();
        Thread thread =
                new Thread(
                        () ->
                                subscriberJedis.subscribe(
                                        Utils.getListener(),
                                        HelloApplication.getInstance().getLobbyCode()));
        HelloApplication.getInstance().addThread(thread);
        thread.start();
        //        HelloApplication.getInstance().returnResource(instance);
    }

    public void makeYourOwn(ActionEvent actionEvent) {
        return;
    }
}
