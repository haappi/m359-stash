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
    @FXML private Label welcomeText;

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

        final Jedis subscriberJedis = HelloApplication.getInstance().getResource();
        Thread thread =
                new Thread(
                        () -> {
                            if (Thread.currentThread().isInterrupted()) {
                                System.out.println("Interrupted");
                                subscriberJedis.close();
                                HelloApplication.getInstance().returnResource(subscriberJedis);
                                Thread.currentThread().interrupt();
                            } else {
                                subscriberJedis.subscribe(
                                        Utils.getListener(), HelloApplication.joinCode());
                            }
                        });
        HelloApplication.getInstance().setRedisClientID(subscriberJedis.clientId());
        HelloApplication.getInstance().setThread(thread);
        name.setText(name.getText().trim().replaceAll(" ", "-"));
        HelloApplication.getInstance()
                .setName(
                        name.getText() != null
                                        || !name.getText().isEmpty()
                                        || !name.getText().equals(" ")
                                ? name.getText()
                                : "Player " + count);
        String stringName = HelloApplication.getInstance().getName();
        NewPlayerJoin packet =
                new NewPlayerJoin(HelloApplication.getInstance().getClientID(), stringName);
        Utils.p(packet);
        HelloApplication.getInstance().setScene("lobby");
        Lobby.addUserToConnected(packet);
        thread.start();
    }
}
