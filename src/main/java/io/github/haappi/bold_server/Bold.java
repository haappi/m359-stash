package io.github.haappi.bold_server;

import io.github.haappi.packets.Card;
import io.github.haappi.packets.FlipCard;
import io.github.haappi.shared.Enums;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Bold {
    /*
     * 5 rows, 4 columns
     * - Add possibility to change grid size upon initialization todo
     * Add a timer todo
     * - The timer can be per player (x seconds), or for the entire game to stress out all players.
     * - Or a combination of both so players do not stall.
     * Special cards todo
     * - Change stuff like timer // gridsize on the fly.
     * - A card that voids half of your card
     * - Shuffles the deck
     * - - todo maybe make it animation so players can see how the cards move around?
     *
     * - "Double or nun" card. Player can choose to play it and they have to make x amount of matches in x amount of time or all their cards are voided.
     */
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

    private void setCards() {
        Enums[] colors = {Enums.BLUE, Enums.ORANGE, Enums.YELLOW};
        Enums[] containers = {Enums.CUP, Enums.BOTTLE, Enums.JAR};
        Enums[] patterns = {Enums.DOTS, Enums.STRIPES, Enums.STARS};
        Enums[] sizes = {Enums.SMALL, Enums.MEDIUM, Enums.LARGE};

        for (Enums color : colors) {
            for (Enums container : containers) {
                for (Enums pattern : patterns) {
                    for (Enums size : sizes) {
                        drawPile.add(new Card(size, color, container, pattern));
                    }
                }
            }
        }
        drawPile.add(new Card());

        Collections.shuffle(drawPile);
    }

    private boolean isMatch(Card card1, Card card2) {
        return card1.equals(card2);
    }

    public boolean isMatch(Card... cards) {
        String[] attributes = {"color", "shape", "container", "size"};
        String matchedAgainst = "";
        for (String attribute : attributes) {
            if (Card.isMatch(attribute, cards)) {
                matchedAgainst = attribute;
                break;
            }
        }

        if (matchedAgainst.equals("")) {
            return false;
        }

        for (Card card : cards) {
            if (!card.isFlipped()) {
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
