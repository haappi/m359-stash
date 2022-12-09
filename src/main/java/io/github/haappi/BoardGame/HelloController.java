package io.github.haappi.BoardGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import redis.clients.jedis.Jedis;

import java.io.IOException;

public class HelloController {
    public TextField joinCode;
    public TextField name;
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

    //    @FXML
    //    protected void initialize() {
    //        Image image = new Image(Utils.getImage("map-of-north-america.png"));
    //        aaa.add(new ImageView(image), 0, 0);
    //        aaa.setGridLinesVisible(true);
    //        aaa.setPrefSize(1000, 1000);
    //    }

    public void connectToGame(ActionEvent actionEvent) throws IOException {
        if (joinCode.getText() == null || joinCode.getText().isEmpty()) {
            return;
        }
        HelloApplication.getInstance().setLobbyCode(joinCode.getText().toLowerCase());
        long count = Utils.getNumCount(HelloApplication.joinCode());
        if (count >= 4) {
            welcomeText.setText("Lobby is full!");
            welcomeText.setTextFill(Color.RED);
            return;
        }
        ((Node) actionEvent.getSource()).setDisable(true);
        //        Jedis instance = HelloApplication.getInstance().getResource();
        /* todo
        some sort of key mapping persistently stored within redis (that gets deleted when application dies or after an hour or so)
        that maps a key to a lobby code
        if game started
        maybe store player things in key mapping?
         */

        final Jedis subscriberJedis = HelloApplication.getInstance().getResource();
        Thread thread =
                new Thread(
                        () -> {
                            if (Thread.currentThread().isInterrupted()) {
                                System.out.println("Interrupted");
                                subscriberJedis.close();
                            } else {
                                subscriberJedis.subscribe(
                                        Utils.getListener(), HelloApplication.joinCode());
                            }
                        });
        thread.start();
        HelloApplication.getInstance().setRedisClientID(subscriberJedis.clientId());
        HelloApplication.getInstance().setThread(thread);
        name.setText(name.getText().trim().replaceAll(" ", "-"));
        HelloApplication.getInstance()
                .setName(name.getText() != null || !name.getText().isEmpty() || !name.getText().equals(" ") ? name.getText() : "Player " + count);
        String stringName = HelloApplication.getInstance().getName();
        NewPlayerJoin packet =
                new NewPlayerJoin(HelloApplication.getInstance().getClientID(), stringName);
        Utils.p(packet);
        HelloApplication.getInstance().setScene("lobby");
        Lobby.addUserToConnected(packet);
    }
}
