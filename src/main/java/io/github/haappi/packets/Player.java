package io.github.haappi.packets;

import io.github.haappi.bold_client.Client;
import io.github.haappi.packets.Card;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -1082792331851702559L;
    private final String playerName;
    private final ArrayList<Card[]> matches = new ArrayList<>();
    private boolean ready = false;
    private boolean turn = false;

    private String serverName;

    private static Player singleton = new Player("null");

    private Player(String playerName) {
        this.playerName = playerName;
        this.serverName = Client.getInstance().getServerAddress();
    }

    public static Player getInstance(String playerName) {
        if (singleton == null) {
            singleton = new Player(playerName);
        }
        return singleton;
    }

    public static Player getInstance() {
        if (singleton == null) {
            singleton = new Player(Client.getInstance().clientName());
        }
        return singleton;
    }

    public static void setPlayer(Player player) {
        singleton = player;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card[]> getMatches() {
        return matches;
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
