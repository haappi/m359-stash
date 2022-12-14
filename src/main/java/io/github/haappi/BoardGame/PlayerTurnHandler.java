package io.github.haappi.BoardGame;

import java.util.ArrayList;

public class PlayerTurnHandler {
    private final Game game;
    private final Player player;
    private int count = 4;
    private final boolean happening = true;

    public PlayerTurnHandler(Game instance, Player player) {
        this.game = instance;
        this.player = player;
    }

    /**
     * "Drives" / "Ferries" the player to the given city
     * This only works for adjacent cities
     */
    public void local(City city) {
        if (this.player.getCurrentCity().getAdjacentCities().contains(city)) {
            this.player.setCurrentCity(city);
            this.count--;
        }
    }

    public void build_research() {
        if (this.player.getCurrentCity().hasResearchStation()) {
            return;
        }
        if (this.game.getResearchStations().size() == 6) {
            return;
        }
        if (this.player.getCards().contains(new CityCard(this.player.getCurrentCity()))) {
            this.player.removeCard(new CityCard(this.player.getCurrentCity()));
            this.player.getCurrentCity().setHasResearchStation(true);
            this.game.addResearchStation(this.player.getCurrentCity());
            this.count--;
        }
    }

    public void flight(City city) {
        if (this.player.getCards().contains(new CityCard(city))) {
            this.player.setCurrentCity(city);
            this.player.removeCard(new CityCard(city));
            this.count--;
        }
    }

    public void charter(City city) {
        if (this.player.getCards().contains(new CityCard(this.player.getCurrentCity()))) {
            this.player.setCurrentCity(city);
            this.player.removeCard(new CityCard(this.player.getCurrentCity()));
            this.count--;
        }
    }

    public void treat_disease(String color) {
        if (this.player.getCurrentCity().getCubes().get(color) > 0) {
            while (this.player.getCurrentCity().getCubes().get(color) > 0) {
                this.player.getCurrentCity().getCubes().put(color, this.player.getCurrentCity().getCubes().get(color) - 1);
                this.game.setCubes(this.player.getCurrentCity().getColor(), this.game.getCubes(this.player.getCurrentCity().getColor()) + 1);
            }
        } else {
            this.player.getCurrentCity().getCubes().put(color, this.player.getCurrentCity().getCubes().get(color) - 1);
            this.game.setCubes(this.player.getCurrentCity().getColor(), this.game.getCubes(this.player.getCurrentCity().getColor()) + 1);
        }
        this.count--;
    }

    public void find_cure(String color, ArrayList<Card> cardToDiscard) {
        if (cardToDiscard)
    }

    public void share_knowledge(Player player, CityCard card) {
        if (this.player.getCurrentCity().equals(player.getCurrentCity())) {
            if (this.player.getCards().contains(card)) {
                this.player.removeCard(card);
                player.addCard(card);
            } else if (player.getCards().contains(card)) {
                player.removeCard(card);
                this.player.addCard(card);
            } else {
            }
        }
    }

}
