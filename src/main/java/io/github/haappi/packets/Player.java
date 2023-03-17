package io.github.haappi.packets;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    @Serial private static final long serialVersionUID = -1082792331851702559L;
    private final String playerName;
    private final ArrayList<Card[]> matches = new ArrayList<>();
    private boolean ready = false;
    private boolean turn = false;

    public Player(String playerName) {
        this.playerName = playerName;
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
