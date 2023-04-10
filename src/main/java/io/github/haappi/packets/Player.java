package io.github.haappi.packets;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = -1082792331851702559L;
    private final ArrayList<Card[]> matches = new ArrayList<>();
    private String playerName;
    private boolean ready = false;
    private boolean turn = false;
    private int score = 0;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public ArrayList<Card[]> getMatches() {
        return matches;
    }

    public boolean isReady() {
        return ready;
    }

    public Player setReady(boolean ready) {
        this.ready = ready;
        return this;
    }

    public boolean isTurn() {
        return turn;
    }

    public Player setTurn(boolean turn) {
        this.turn = turn;
        return this;
    }
}
