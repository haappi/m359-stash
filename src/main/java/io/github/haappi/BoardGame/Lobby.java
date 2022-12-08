package io.github.haappi.BoardGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.Objects;

public class Lobby {
    public Label connectedTo;
    public ListView<ConnectedUser> connectedPlayers;
    public Label playersLabel;
    public Button readyButton;
    private boolean isPlayerReady = false;
    private static ListView<ConnectedUser> connectedPlayersLocal;

    @FXML
    protected void initialize() {
        Lobby.connectedPlayersLocal = connectedPlayers;
    }

    public void leaveLobby(ActionEvent actionEvent) {
        Utils.p(new UserLeft());
    }

    public void ready(ActionEvent actionEvent) {
        isPlayerReady = !isPlayerReady;
        ((Button) actionEvent.getSource())
                .setText(isPlayerReady ? "I am ready." : "I am not ready");
        Utils.p(new PlayerUnreadyReady(isPlayerReady));
    }

    public static void addUserToConnected(NewPlayerJoin packet) {
        if (checkIfPlayerAlreadyConnected(packet)) {
            return;
        }
        Platform.runLater(
                () ->
                        connectedPlayersLocal
                                .getItems()
                                .add(new ConnectedUser(packet.getUUID(), packet.getUserName())));
    }

    private static boolean checkIfPlayerAlreadyConnected(NewPlayerJoin packet) {
        for (ConnectedUser connectedUser : Lobby.connectedPlayersLocal.getItems()) {
            System.out.println(connectedUser);
            if (Objects.equals(connectedUser.getUUID(), packet.getUUID())) {
                return true;
            }
        }
        return false;
    }
}
