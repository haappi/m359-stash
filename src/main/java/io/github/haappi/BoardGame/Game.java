package io.github.haappi.BoardGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Game {
    final City WHITEHORSE = getCity("Whitehorse", "red", 9, 5);
    final City EDMONTON = getCity("Edmonton", "blue", 13, 8);
    final City QUEBEC = getCity("Quebec", "yellow", 19, 10);

    final City VANCOUVER = getCity("Vancouver", "red", 10, 9);
    final City SEATTLE = getCity("Seattle", "blue", 10, 10);
    final City SAN_FRANCISCO = getCity("San Francisco", "yellow", 10, 12);

    final City SALT_LAKE_CITY = getCity("Salt Lake City", "red", 13, 11);
    final City DENVER = getCity("Denver", "blue", 14, 12);
    final City DALLAS = getCity("Dallas", "yellow", 15, 14);

    final City ATLANTA = getCity("Atlanta", "red", 18, 14);
    final City MIAMI = getCity("Miami", "blue", 19, 16);
    final City NEW_YORK_CITY = getCity("New York City", "yellow", 20, 11);

    final City CHICAGO = getCity("Chicago", "red", 17, 12);
    final City ONTARIO = getCity("Ontario", "blue", 15, 10);
    private final HashMap<String, Integer> cubes = new HashMap<>();
    private final ArrayList<Card> playerDiscardPile = new ArrayList<>();
    private final ArrayList<Card> possiblePlayerCards = new ArrayList<>();
    private final ArrayList<String> eradicatedDiseases = new ArrayList<>();
    private final ArrayList<City> outbreakCities = new ArrayList<>();
    private final ArrayList<String> curedDiseases = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<City> cities =
            new ArrayList<>(
                    List.of(
                            WHITEHORSE,
                            EDMONTON,
                            QUEBEC,
                            VANCOUVER,
                            SEATTLE,
                            SAN_FRANCISCO,
                            SALT_LAKE_CITY,
                            DENVER,
                            DALLAS,
                            ATLANTA,
                            MIAMI,
                            NEW_YORK_CITY,
                            CHICAGO,
                            ONTARIO)
            );
    private final HashMap<City, ArrayList<City>> adjacentCities = new HashMap<>();
    private final int infectionRate = 0;
    InfectionThings infectionThings = new InfectionThings(this);
    private int turns = 0;
    private int outbreakCounter = 0;
    public Game() throws IOException {
        for (City city : this.cities) {
            this.possiblePlayerCards.add(new CityCard(city));
        }

        adjacentCities.put(WHITEHORSE, new ArrayList<>(List.of(EDMONTON, VANCOUVER)));
        adjacentCities.put(
                EDMONTON, new ArrayList<>(List.of(VANCOUVER, SALT_LAKE_CITY, ONTARIO, QUEBEC)));
        adjacentCities.put(
                QUEBEC, new ArrayList<>(List.of(EDMONTON, NEW_YORK_CITY, CHICAGO, ONTARIO)));
        adjacentCities.put(
                VANCOUVER, new ArrayList<>(List.of(WHITEHORSE, EDMONTON, SEATTLE, SALT_LAKE_CITY)));

        adjacentCities.put(
                SEATTLE, new ArrayList<>(List.of(VANCOUVER, SAN_FRANCISCO, SALT_LAKE_CITY)));
        adjacentCities.put(
                SAN_FRANCISCO, new ArrayList<>(List.of(SEATTLE, SALT_LAKE_CITY, DENVER, DALLAS)));
        adjacentCities.put(
                SALT_LAKE_CITY, new ArrayList<>(List.of(SEATTLE, SAN_FRANCISCO, DENVER, ONTARIO)));
        adjacentCities.put(
                DENVER,
                new ArrayList<>(
                        List.of(SALT_LAKE_CITY, SAN_FRANCISCO, DALLAS, CHICAGO, ONTARIO, ATLANTA)));

        adjacentCities.put(DALLAS, new ArrayList<>(List.of(CHICAGO, DENVER, ATLANTA, MIAMI)));
        adjacentCities.put(ATLANTA, new ArrayList<>(List.of(DALLAS, CHICAGO, MIAMI)));
        adjacentCities.put(MIAMI, new ArrayList<>(List.of(ATLANTA, DALLAS)));
        adjacentCities.put(NEW_YORK_CITY, new ArrayList<>(List.of(CHICAGO, QUEBEC, ATLANTA)));

        adjacentCities.put(
                CHICAGO, new ArrayList<>(List.of(NEW_YORK_CITY, QUEBEC, DENVER, DALLAS, ATLANTA)));
        adjacentCities.put(
                ONTARIO, new ArrayList<>(List.of(QUEBEC, EDMONTON, DENVER, SALT_LAKE_CITY)));


        Utils.getCityByName("Atlanta", this).setHasResearchStation(true);
        this.cubes.put("red", 24);
        this.cubes.put("blue", 24);
        this.cubes.put("yellow", 24);
        this.cubes.put("black", 24);

        for (int i = 0; i < 2; i++) {
            // loop 2 times
            // infect that city with 2 cubes
            City city = infectionThings.getInfectionCity();
            for (int j = 0; j < 2; j++) {
                city.doInfect();
            }
        }

        for (int i = 0; i < 1; i++) {
            City city = infectionThings.getInfectionCity();
            for (int j = 0; j < 1; j++) {
                city.doInfect();
            }
        }
    }

    public ArrayList<Card> getPossiblePlayerCards() {
        return possiblePlayerCards;
    }

    public int getPossiblePlayerCardsSize() {
        return possiblePlayerCards.size();
    }

    public City getCity(String name, String color, int i, int j) {
        return new City(this, name, color, i, j);
    }

    public City getCityByName(String name) {
        for (City city : cities) {
            if (city.getCityName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    public int getOutbreakCounter() {
        return outbreakCounter;
    }

    public void addOutbreak() {
        outbreakCounter++;
    }

    public ArrayList<String> getEradiatedDiseases() {
        return this.eradicatedDiseases;
    }

    public int getCubeCount(String color) {
        return this.cubes.getOrDefault(color, 24);
    }

    public void decrementCubeCount(String color) {
        this.cubes.put(color, this.cubes.getOrDefault(color, 24) - 1);
    }

    public void incrementCubeCount(String color) {
        this.cubes.put(color, this.cubes.getOrDefault(color, 24) + 1);
    }

    public void addCardToDiscard(Card card) {
        this.playerDiscardPile.add(card);
    }

    public int getPerTurnInfectionRate() {
        if (this.infectionRate < 3) {
            return 2;
        } else if (this.infectionRate < 5) {
            return 3;
        } else {
            return 4;
        }
    }

    public boolean checkForEradication(String color) {
        if (this.cubes.get(color) == 24) {
            this.eradicatedDiseases.add(color);
            return true;
        }
        return this.eradicatedDiseases.contains(color);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void clearOutbreak() {
        this.outbreakCities.clear();
    }

    public void endGame(String reason) throws IOException {
        EndGame.reason = reason;
        HelloApplication.setScene("end-game");
    }

    public ArrayList<City> getCities(City city) {
        return this.adjacentCities.get(city);
    }

    public ArrayList<City> getAdjacentCities(City city) {
        return this.adjacentCities.get(city);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void startGame() {
        Collections.shuffle(this.possiblePlayerCards);
        for (Player p : this.players) {
            p.setHand(this.possiblePlayerCards.subList(0, 3)); // 3 cards
            this.possiblePlayerCards.subList(0, 3).clear();
        }
    }

    public Player getCurrentPlayer() {
        return this.players.get(turns % this.players.size());
    }

    public Player nextPlayer() {
        turns++;
        Player player = this.players.get(turns % this.players.size());
        PlayerTurn playerTurn = new PlayerTurn(this, player);
        player.setPlayerTurn(playerTurn);


        BoardView.updateExtraInformation();
        updateStuff();

        return player;
    }

    public void updateStuff() {
        BoardView.personTurnStatic.setText("It is " + getCurrentPlayer().getName() + "'s turn");
        BoardView.actionsViewStatic.getItems().clear();
        BoardView.cardViewStatic.getItems().clear();
        BoardView.actionsViewStatic.getItems().addAll(getCurrentPlayer().getActions());
        BoardView.cardViewStatic.getItems().addAll(getCurrentPlayer().getHand());
        BoardView.actionsLabelStatic.setText("Actions (" + getCurrentPlayer().getPlayerTurn().getRemainingActions() + ")");

        BoardView.updateExtraInformation();

    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
