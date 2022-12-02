package io.github.haappi.BoardGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

public class HelloController {
    public TextField joinCode;
    Thread threadd;
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
        // todo check how many players are in the given lobby code
        // todo if it's already at 4 players, inform the user that the lobby they specified is arelady full.
        //        ((Node) actionEvent.getSource()).setDisable(true);
        HelloApplication.getInstance().setLobbyCode(joinCode.getText().toLowerCase());

        //        Jedis instance = HelloApplication.getInstance().getResource();
        /* todo
        some sort of key mapping persistently stored within redis (that gets deleted when application dies or after an hour or so)
        that maps a key to a lobby code
        if game started
        maybe store player things in key mapping?
         */

        //        Utils.p(new Test(HelloApplication.getInstance().getClientID()));

        Utils.p(new ConnectedUser(HelloApplication.getInstance().getClientID(), "haappi"));
        final Jedis subscriberJedis = HelloApplication.getInstance().getResource();
        Thread thread =
                new Thread(
                        () ->
                                subscriberJedis.subscribe(
                                        Utils.getListener(),
                                        HelloApplication.getInstance().getLobbyCode()));
        thread.start();
        //        threadd = new Thread(() -> );
        // fixme this will always return 0 if you immediately run it after starting the thread.
        System.out.println(
                subscriberJedis.sendCommand(
                        Protocol.Command.PUBSUB,
                        "NUMSUB",
                        HelloApplication.getInstance().getLobbyCode()));
        //        HelloApplication.getInstance().returnResource(instance);
    }

    public void makeYourOwn(ActionEvent actionEvent) {
        System.out.println(
                HelloApplication.getInstance()
                        .getResource()
                        .sendCommand(
                                Protocol.Command.PUBSUB,
                                "NUMSUB",
                                HelloApplication.getInstance().getLobbyCode()));
        return;
    }
}
