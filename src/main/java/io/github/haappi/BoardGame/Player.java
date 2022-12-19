package io.github.haappi.BoardGame;

import io.github.haappi.BoardGame.Actions.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final Game game;
    private final ArrayList<Card> hand = new ArrayList<>();
    private final Color color;
    private PlayerTurn playerTurn;
    //    private City currentCity = Utils.ATLANTA;
    private City currentCity;

    public Player(Game gameInstance, String name, Color color) {
        this.game = gameInstance;
        this.name = name;
        this.setCurrentCity(gameInstance.getCityByName("Atlanta"));
        this.color = color;
    }

    public PlayerTurn getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(PlayerTurn playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand.addAll(hand);
    }

    public boolean playerHasCityCard(City city) {
        for (Card card : hand) {
            if (card instanceof CityCard) {
                if (((CityCard) card).getCity().equals(city)) {
                    return true;
                }
            }
        }
        return false;
    }

    public CityCard getCityCard(City city) {
        for (Card card : hand) {
            if (card instanceof CityCard) {
                if (((CityCard) card).getCity().equals(city)) {
                    return (CityCard) card;
                }
            }
        }
        return null;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        int i;
        int j;
        if (this.currentCity != null) {
            i = this.currentCity.getI();
            j = this.currentCity.getJ();
            BoardView.rectangles[i][j].setFill(Color.WHITE);
            BoardView.rectangles[i][j].setOpacity(0);
        }
        this.currentCity = currentCity;

        i = this.currentCity.getI();
        j = this.currentCity.getJ();

        if (BoardView.rectangles[i][j] != null) {
            BoardView.rectangles[i][j].setFill(color);
            BoardView.rectangles[i][j].setOpacity(100);
        }

    }

    public void discardACard(Card card) {
        this.hand.remove(card);
        this.game.addCardToDiscard(card);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Action> getActions() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new BuildResearchStation(this.playerTurn));
        actions.add(new CharterFlight(this.playerTurn));
        actions.add(new DirectFlight(this.playerTurn));
        actions.add(new Drive(this.playerTurn));
        actions.add(new RemoveResearchStation(this.playerTurn));
        actions.add(new ShuttleFlight(this.playerTurn));

        return actions;
    }

    public void addCardToHand(Card card) {
        this.hand.add(card);
    }
}
