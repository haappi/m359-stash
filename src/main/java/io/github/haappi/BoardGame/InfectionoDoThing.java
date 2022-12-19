package io.github.haappi.BoardGame;

import java.io.IOException;
import java.util.ArrayList;

public class InfectionoDoThing {
    private final Game game;
    private final ArrayList<Card> playerHand;


    public InfectionoDoThing(Game game, ArrayList<Card> playerHand) throws IOException {
        this.game = game;
        this.playerHand = playerHand;
        if (game.getPossiblePlayerCardsSize() < 3) {
            EndGame.reason = "No more player cards left to draw; you lose!";
            HelloApplication.setScene("end-game");
        }
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
}
