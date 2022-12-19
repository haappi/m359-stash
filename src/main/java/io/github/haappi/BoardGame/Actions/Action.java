package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.Player;
import io.github.haappi.BoardGame.PlayerTurn;

public class Action {
    private final String name;
    private final PlayerTurn playerTurn;
    private final Player player;

    public Action(String name, PlayerTurn playerTurn) {
        this.name = name;
        this.playerTurn = playerTurn;
        this.player = playerTurn.getPlayer();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public PlayerTurn getPlayerTurn() {
        return playerTurn;
    }

    public Player getPlayer() {
        return player;
    }

    public void doAction() {
        this.playerTurn.decrementRemainingActions();
        if (this.playerTurn.getRemainingActions() == 0) {
            this.player.getGame().nextPlayer();
        }
    }

    public void incrementRemainingActions() {
        this.playerTurn.incrementRemainingActions();
    }
}
