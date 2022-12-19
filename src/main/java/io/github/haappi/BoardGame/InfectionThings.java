package io.github.haappi.BoardGame;

import java.util.ArrayList;

public class InfectionThings extends Card {
    private final Game game;
    private final ArrayList<City> cities;
    private final ArrayList<City> discarded = new ArrayList<>();


    public InfectionThings(Game gameInstance) {
        this.game = gameInstance;
        this.cities = gameInstance.getCities();
    }

    /**
     * Gets the top card from the infection deck & resets the outbreak array.
     *
     * @return A {@link City}.
     */
    public City getInfectionCity() {
        game.clearOutbreak();
        City city = cities.get(0);
        cities.remove(0);
        discarded.add(city);
        return city;
    }
}
