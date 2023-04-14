package io.github.haappi.bold_server;

import io.github.haappi.packets.Card;
import io.github.haappi.shared.Enums;

import java.util.ArrayList;
import java.util.Collections;

public class Bold {
    private final Card[][] cards = new Card[4][5];
    private final ArrayList<Card> drawPile = new ArrayList<>();
    private final Server server;
    private final int modifier;

    private final ArrayList<Card> selectedCards = new ArrayList<>();
    private ClientHandler currentPlayer;

    private boolean gameOver = false;

    public Bold(Server server, int modifier) {
        this.server = server;
        this.modifier = modifier;

        setDeckCards();
        Collections.shuffle(drawPile);
    }

    public Bold(Server server) {
        this(server, 1);
    }

    private void restartGame() {
        if (!gameOver) {
            return;
        }
        gameOver = false;

        for (ClientHandler client : server.getClients()) {
            client.setScore(0);
        }

        for (ClientHandler client : server.getClients()) {
            client.sendMessage("score:" + client.getPlayerScore());
        }
        setDeckCards();
        Collections.shuffle(drawPile);
        start();
    }

    public void selectCard(int row, int col) {
        Card card = cards[row][col];
        selectedCards.add(card);

        server.broadcast(card);
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

    public boolean match(ArrayList<Card> selectedCards) {
        Enums size = selectedCards.get(0).getSize();
        Enums color = selectedCards.get(0).getColor();
        Enums container = selectedCards.get(0).getContainer();
        Enums pattern = selectedCards.get(0).getPattern();

        for (int i = 1; i < selectedCards.size(); i++) {
            Card card = selectedCards.get(i);
            if (card.getSize() != size && card.getColor() != color
                    && card.getContainer() != container && card.getPattern() != pattern) {
                return false;
            }
        }
        return true;
    }

    public boolean match(int x, int y) {
        ArrayList<Card> selectedCards = new ArrayList<>(this.selectedCards);
        selectedCards.add(cards[x][y]);
        return match(selectedCards);
    }


    public void createGameDeck() {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length; j++) {
                Card card = drawPile.remove(0);
                cards[i][j] = card;
                card.setRow(i);
                card.setCol(j);
            }
        }

        server.broadcast(cards);
    }

    public void nextLosersTurn() {
        currentPlayer.increaseScore((int) (Math.pow(selectedCards.size(), selectedCards.size()) * modifier));

        for (ClientHandler client : server.getClients()) {
            client.sendMessage("score:" + client.getPlayerScore());
        }

        int currentIndex = server.getClients().indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % server.getClients().size();
        currentPlayer = server.getClients().get(nextIndex);


        if (selectedCards.size() >= 2) { // if they have selected atleast two cards, that must mean it was a match of some sort
            for (Card card : selectedCards) {
                Card newCard = drawPile.remove(0);
                cards[card.getRow()][card.getCol()] = newCard;
                newCard.setCol(card.getCol());
                newCard.setRow(card.getRow());
            }
            if (drawPile.size() == 0) {
                server.broadcast("gameOver");
            }
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    server.broadcast(cards);
                    server.broadcast("flipAllCards");

                    Thread.sleep(1000);

                    for (ClientHandler client : server.getClients()) {
                        if (client == currentPlayer) {
                            client.sendMessage("yourTurn");
                        } else {
                            client.sendMessage("notYourTurn");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        selectedCards.clear();


    }

    public void start() {
        createGameDeck();
        currentPlayer = server.getClients().get(0);
        for (ClientHandler client : server.getClients()) {
            if (client == currentPlayer) {
                client.sendMessage("yourTurn");
            } else {
                client.sendMessage("notYourTurn");
            }
        }

    }

    public boolean isGameCompleted() {
        if (drawPile.size() > 0) {
            return false;
        }

        for (Card[] value : cards) {
            for (Card card : value) {
                if (card != null && canCardBeMatched(card)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canCardBeMatched(Card card) {
        // somehow make this method faster
        for (int row = 0; row < cards.length; row++) {
            for (int col = 0; col < cards[row].length; col++) {
                if (row != card.getRow() || col != card.getCol()) {
                    Card otherCard = cards[row][col];
                    if (otherCard != null && doCardsMatch(card, otherCard)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean doCardsMatch(Card card1, Card card2) {
        return card1.getSize() == card2.getSize() ||
                card1.getColor() == card2.getColor() ||
                card1.getContainer() == card2.getContainer() ||
                card1.getPattern() == card2.getPattern();
    }
}
