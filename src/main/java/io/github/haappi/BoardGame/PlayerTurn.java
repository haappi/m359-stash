package io.github.haappi.BoardGame;

import io.github.haappi.BoardGame.Actions.Action;

import java.util.ArrayList;

public class PlayerTurn {
    private final Game game;
    private final Player player;
    private final ArrayList<Action> actions = new ArrayList<>();
    private int remainingActions = 4;

    public PlayerTurn(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public int getRemainingActions() {
        return remainingActions;
    }

    public void decrementRemainingActions() {
        this.remainingActions--;
    }

    public Player getPlayer() {
        return player;
    }

    public void incrementRemainingActions() {
        this.remainingActions++;
    }
}
