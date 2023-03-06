package io.github.haappi.bold_server;

import io.github.haappi.packets.Card;
import io.github.haappi.packets.FlipCard;

import java.io.IOException;
import java.util.ArrayList;

public class Bold {
    private final Card[][] cards = new Card[5][4];
    private final ArrayList<Card> drawPile = new ArrayList<>();
    private final Server server;
    private final int modifier;

    public Bold(Server server) {
        this.server = server;
        modifier = 0; // No modifier
    }

    public Bold(Server server, int modifier) {
        this.server = server;
        this.modifier = modifier;
    }

    private boolean isMatch(Card card1, Card card2) {
        return card1.equals(card2);
    }

    public boolean isMatch(Card ... cards) {
        for (Card card : cards) {
            if (!isMatch(cards[0], card)) {
                return false;
            }
        }
        return true;
    }

    public void flipCard(Card card) throws IOException {
        card.flip();

        server.broadcast(new FlipCard(card));
    }
}
