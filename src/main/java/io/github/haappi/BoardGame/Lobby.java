package io.github.haappi.BoardGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ClientKillParams;

import java.io.IOException;
import java.util.*;

public class Lobby {
    private static boolean isPlayerReady = false;
    private static ListView<ConnectedUser> connectedPlayersLocal;
    private static Button startGameButtonLocal;
    public Label connectedTo;
    public ListView<ConnectedUser> connectedPlayers;
    public Button readyButton;
    public Button startGameButton;

    public static void removeUserFromConnected(UserLeft userLeft) {
        Platform.runLater(
                () -> {
                    for (int i = 0; i < connectedPlayersLocal.getItems().size(); i++) {
                        if (Objects.equals(
                                connectedPlayersLocal.getItems().get(i).getUUID(),
                                userLeft.getUUID())) {
                            connectedPlayersLocal.getItems().remove(i);
                            break;
                        }
                    }
                });
    }

    public static <T> ArrayList<ConnectedUser> getConnectedUsers() {
        List list = new ArrayList(Arrays.asList(connectedPlayersLocal.getItems().toArray()));
        Collections.sort(list);
        return new ArrayList<ConnectedUser>(list);
    }

    public static void updatePlayerReady(PlayerUnreadyReady playerUnreadyReady) {
        Platform.runLater(
                () -> {
                    for (ConnectedUser connectedUser : connectedPlayersLocal.getItems()) {
                        if (Objects.equals(connectedUser.getUUID(), playerUnreadyReady.getUUID())) {
                            connectedUser.setReady(playerUnreadyReady.isReady());
                            connectedPlayersLocal.refresh(); // Refresh the list view
                            // Updates all the players in the list view (their toStrings)
                            break;
                        }
                    }
                    for (ConnectedUser connectedUser : connectedPlayersLocal.getItems()) {
                        if (!connectedUser.isReady()) {
                            startGameButtonLocal.setDisable(true);
                            return;
                        }
                    }
                    startGameButtonLocal.setDisable(false);
                });
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

    @FXML
    protected void initialize() {
        connectedTo.setText("Connected to " + HelloApplication.getInstance().getLobbyCode());
        Lobby.connectedPlayersLocal = connectedPlayers;
        Lobby.isPlayerReady = false;
        Lobby.startGameButtonLocal = startGameButton;
//        Lobby.connectedPlayersLocal.getItems().add(new ConnectedUser())
    }

    public void leaveLobby(ActionEvent actionEvent) throws IOException {
        Utils.p(new UserLeft());
        HelloApplication.getInstance().setLobbyCode(null);
        HelloApplication.getInstance().setScene("hello-view");
        Jedis resource = HelloApplication.getInstance().getResource();
        resource.clientKill(
                ClientKillParams.clientKillParams()
                        .id(HelloApplication.getInstance().getRedisClientID() + ""));
        HelloApplication.getInstance().getThread().interrupt();
    }

    public void ready(ActionEvent actionEvent) {
        isPlayerReady = !isPlayerReady;
        ((Button) actionEvent.getSource())
                .setText(isPlayerReady ? "I am not ready." : "I am ready");
        Utils.p(
                new PlayerUnreadyReady(
                        isPlayerReady, HelloApplication.getInstance().getClientID()));
    }

    public void startGame(ActionEvent event) {
        ConnectedUser connectedUser = new ConnectedUser("test", "test");
        for (ConnectedUser connectedUser1 : connectedPlayers.getItems()) {
            if (Objects.equals(connectedUser1.getUUID(), HelloApplication.getInstance().getClientID())) {
                connectedUser = connectedUser1;
                break;
            }
        }
        if (Objects.equals(connectedUser.getUUID(), "test")) {
            Utils.p(new LoseScreenPacket("An error occurred in the lobby."));
            return;
        }
        connectedUser.setHost(true);

        Utils.p(new StartGamePacket());
    }
}
