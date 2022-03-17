package io.github.haappi.bold_server;

import io.github.haappi.packets.Card;
import io.github.haappi.packets.Player;
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

    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Card> selectedCards = new ArrayList<>();
    private Player currentPlayer;

    public Bold(Server server, int modifier) {
        this.server = server;
        this.modifier = modifier;

        setDeckCards();
        Collections.shuffle(drawPile);
        createGameDeck();
    }

    public Bold(Server server) {
        this(server, 0);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void selectCard(int row, int col) {
        Card card = cards[row][col];
        selectedCards.add(card);
        for (ClientHandler player : server.getClients()) {
            player.sendObject(card);
        }
    }

    private void setDeckCards() {
        drawPile.clear();

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
    }

//    private boolean isMatch(Card card1, Card card2) {
//        return card1.equals(card2);
//    }
//
//    public boolean isMatch(Card... cards) {
//        String[] attributes = {"color", "shape", "container", "size"};
//        String matchedAgainst = "";
//        for (String attribute : attributes) {
//            if (Card.isMatch(attribute, cards)) {
//                matchedAgainst = attribute;
//                break;
//            }
//        }
//
//        if (matchedAgainst.equals("")) {
//            return false;
//        }
//
//        for (Card card : cards) {
//            if (card.isFlipped()) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    public boolean doCardsMatch() {

        String sizeCheck = "";
        String colorCheck = "";
        String containerCheck = "";
        String patternCheck = "";

        for (Card card : selectedCards) {
            if (sizeCheck.equals("")) {
                sizeCheck = card.getSize().toString();
            } else if (!sizeCheck.equals(card.getSize().toString())) {
                return false;
            }
            if (colorCheck.equals("")) {
                colorCheck = card.getColor().toString();
            } else if (!colorCheck.equals(card.getColor().toString())) {
                return false;
            }
            if (containerCheck.equals("")) {
                containerCheck = card.getContainer().toString();
            } else if (!containerCheck.equals(card.getContainer().toString())) {
                return false;
            }
            if (patternCheck.equals("")) {
                patternCheck = card.getPattern().toString();
            } else if (!patternCheck.equals(card.getPattern().toString())) {
                return false;
            }
        }
        return true;
    }

    public void createGameDeck() {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length; j++) {
                cards[i][j] = drawPile.remove(0);
            }
        }

        server.broadcast(cards);
    }

    public void nextLosersTurn() {
        currentPlayer.setScore((int) (currentPlayer.getScore() + Math.pow(selectedCards.size(), selectedCards.size())));

        for (ClientHandler client : server.getClients()) {
            client.sendMessage("score:" + client.getPlayer().getScore());
        }

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(currentPlayer)) {
                if (i == players.size() - 1) {
                    currentPlayer = players.get(0);
                } else {
                    currentPlayer = players.get(i + 1);
                }
                break;
            }
        }

        if (selectedCards.size() >= 2) { // if they have selected atleast two cards, that must mean it was a match of some sort
            for (Card card : selectedCards) {
                Card newCard = drawPile.remove(0);
                cards[card.getRow()][card.getCol()] = newCard;
                newCard.setCol(card.getCol());
                newCard.setRow(card.getRow());
            }
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    server.broadcast(cards);
                    server.broadcast("flipAllCards");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        selectedCards.clear();

        for (ClientHandler client : server.getClients()) {
            if (client.getPlayer() == currentPlayer) {
                client.sendMessage("yourTurn");
            } else {
                client.sendMessage("notYourTurn");
            }
        }

    }

    public void start() throws IOException {
        createGameDeck();
    }
}
