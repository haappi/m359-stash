package io.github.haappi.bold_server;

import io.github.haappi.packets.Card;
import io.github.haappi.shared.Enums;

import java.util.*;
import java.util.stream.Collectors;

public class Bold {
    private final Card[][] cards = new Card[4][5];
    private final ArrayList<Card> drawPile = new ArrayList<>();
    private final Server server;
    private final int modifier;

    private final ArrayList<Card> selectedCards = new ArrayList<>();
    private ClientHandler currentPlayer;

    private final boolean gameOver = false;

    public Bold(Server server, int modifier) {
        this.server = server;
        this.modifier = modifier;

        setDeckCards();
        Collections.shuffle(drawPile);
    }

    public Bold(Server server) {
        this(server, 1);
    }

    public void restartGame() {
//        if (!gameOver) {
//            return;
//        }
//        gameOver = false;

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
        boolean sizeMatch = true;
        boolean colorMatch = true;
        boolean containerMatch = true;
        boolean patternMatch = true;

        Enums size = null;
        Enums color = null;
        Enums container = null;
        Enums pattern = null;

        for (int i = 1; i < selectedCards.size(); i++) {
            Card card = selectedCards.get(i);

            if (card == null) {
                return false;
            }

            if (size == null) {
                size = card.getSize();
            } else if (size != card.getSize()) {
                sizeMatch = false;
            }

            if (color == null) {
                color = card.getColor();
            } else if (color != card.getColor()) {
                colorMatch = false;
            }

            if (container == null) {
                container = card.getContainer();
            } else if (container != card.getContainer()) {
                containerMatch = false;
            }

            if (pattern == null) {
                pattern = card.getPattern();
            } else if (pattern != card.getPattern()) {
                patternMatch = false;
            }
        }

        System.out.println(size + " " + color + " " + container + " " + pattern);

        return sizeMatch || colorMatch || containerMatch || patternMatch;
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
        int score = selectedCards.size() * selectedCards.size();
        currentPlayer.increaseScore(score);
        System.out.println(currentPlayer.getPlayerName() + " got " + score + " points");

        for (ClientHandler client : server.getClients()) {
            client.sendMessage("score:" + client.getPlayerScore());
        }

        int currentIndex = server.getClients().indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % server.getClients().size();
        currentPlayer = server.getClients().get(nextIndex);


//        if (selectedCards.size() >= 2) { // if they have selected atleast two cards, that must mean it was a match of some sort
        if (drawPile.size() == 0) {
            if (isGameCompleted()) {
                HashMap<String, Integer> playerScores = new HashMap<>();
                for (ClientHandler connectedClient : server.getClients()) {
                    playerScores.put(connectedClient.getPlayerName(), connectedClient.getPlayerScore());
                }
                // took the below from a stackoverflow thread
                HashMap<String, Integer> sorted = playerScores.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                StringBuilder toStrings = new StringBuilder();
                for (Map.Entry<String, Integer> entrySet : sorted.entrySet()) {
                    toStrings.append(entrySet.getKey()).append(": ").append(entrySet.getValue()).append("\n");
                }
                server.broadcast("gameOver:" + toStrings);
            }
        }
        if (selectedCards.size() >= 2) {
            for (Card card : selectedCards) {
                Card newCard = drawPile.size() > 0 ? drawPile.remove(0) : null;
                cards[card.getRow()][card.getCol()] = newCard;
                if (newCard != null) {
                    newCard.setCol(card.getCol());
                    newCard.setRow(card.getRow());
                }
            }
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
//        }

        selectedCards.clear();


    }

    public void start() {
        createGameDeck();
        currentPlayer = server.getClients().get(0);
        for (ClientHandler client : server.getClients()) {
            client.sendMessage("score:" + client.getPlayerScore());
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
        if (card1 == card2) {
            return false;
        }
        if (card1.getRow() == card2.getRow() && card1.getCol() == card2.getCol()) {
            return false;
        }
        return card1.getSize() == card2.getSize() ||
                card1.getColor() == card2.getColor() ||
                card1.getContainer() == card2.getContainer() ||
                card1.getPattern() == card2.getPattern();
    }

    public void clearDeck() {
        drawPile.clear();
    }
}
