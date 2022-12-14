package io.github.haappi.BoardGame;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card> cards = new ArrayList<>();
    private City currentCity;

    public Player(String name, City currwentCity) {
        this.name = name;
        this.currentCity = currwentCity;
    }

    public String getName() {
        return name;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City city) {
        this.currentCity = city;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public void removeCard(String cardName) {
        for (Card card : this.cards) {
            if (card.getName().equals(cardName)) {
                this.cards.remove(card);
                break;
            }
        }
    }
}
