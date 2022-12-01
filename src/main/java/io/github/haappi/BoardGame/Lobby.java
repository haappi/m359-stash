package io.github.haappi.BoardGame;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Lobby {
    public Label connectedTo;
    public ListView<ConnectedUser> connectedPlayers;
    public Label playersLabel;
    public Button readyButton;
    private boolean isPlayerReady = false;

    public void leaveLobby(ActionEvent actionEvent) {
        Utils.p(new UserLeft());
    }

    public void ready(ActionEvent actionEvent) {
        isPlayerReady = !isPlayerReady;
        ((Button) actionEvent.getSource())
                .setText(isPlayerReady ? "I am ready." : "I am not ready");
        Utils.p(new PlayerUnreadyReady(isPlayerReady));
    }
}
