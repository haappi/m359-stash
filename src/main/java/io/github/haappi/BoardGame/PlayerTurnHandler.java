package io.github.haappi.BoardGame;

import java.util.ArrayList;

public class PlayerTurnHandler {
    private final Game game;
    private final Player player;
    private final boolean happening = true;
    private int count = 4;

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
            Utils.p(new InformationMessage("This city already has a research station"));
            return;
        }
        if (this.game.getResearchStations().size() == 6) {
            Utils.p(new InformationMessage("There are no more research stations left"));
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
                this.player
                        .getCurrentCity()
                        .getCubes()
                        .put(color, this.player.getCurrentCity().getCubes().get(color) - 1);
                this.game.setCubes(
                        this.player.getCurrentCity().getColor(),
                        this.game.getCubes(this.player.getCurrentCity().getColor()) + 1);
            }
        } else {
            this.player
                    .getCurrentCity()
                    .getCubes()
                    .put(color, this.player.getCurrentCity().getCubes().get(color) - 1);
            this.game.setCubes(
                    this.player.getCurrentCity().getColor(),
                    this.game.getCubes(this.player.getCurrentCity().getColor()) + 1);
        }
        this.count--;
    }

    public void find_cure(String color, ArrayList<Card> cardToDiscard) {
        if (!this.player.getCurrentCity().hasResearchStation()) {
            Utils.p(new InformationMessage("This city does not have a research station"));
            return;
        }
        if (this.game.getCuredDiseases().contains(color)) {
            Utils.p(new InformationMessage("This disease is already cured"));
            return;
        }
        if (cardToDiscard.size() != 5) {
            Utils.p(new InformationMessage("You need to discard 5 cards"));
            return;
        }
        for (Card card : cardToDiscard) {
            if (!this.player.getCards().contains(card)) {
                Utils.p(new InformationMessage("You do not have all the cards"));
                return;
            }
        }
        ArrayList<CityCard> cards = new ArrayList<>();
        for (Card card : cardToDiscard) {
            cards.add((CityCard) card);
        }
        for (CityCard card : cards) {
            if (!card.getCity().getColor().equals(color)) {
                Utils.p(new InformationMessage("You need to discard 5 cards of the same color"));
                return;
            }
        }
        for (Card card : cardToDiscard) {
            this.player.removeCard(card);
        }

        this.game.getCuredDiseases().add(color);

        if (this.game.getCuredDiseases().size() == 4) {
            Utils.p(new GameWonPacket("You have cured all the diseases! You win the game!"));
            return;
        }

        this.count--;
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
