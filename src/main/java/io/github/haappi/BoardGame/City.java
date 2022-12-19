package io.github.haappi.BoardGame;

import java.io.IOException;
import java.util.HashMap;

public class City {
    private final Game game;
    private final String color;
    private final String cityName;
    private final HashMap<String, Integer> cubes = new HashMap<>();
    private boolean hasResearchStation = false;
    private int i;
    private int j;

    public City(Game gameInstance, String name, String color, int i, int j) {
        this.game = gameInstance;
        this.cityName = name;
        this.color = color;
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isHasResearchStation() {
        return hasResearchStation;
    }

    public void setHasResearchStation(boolean hasResearchStation) {
        this.hasResearchStation = hasResearchStation;
    }

    public Game getGame() {
        return game;
    }

    public String getColor() {
        return color;
    }

    public HashMap<String, Integer> getCubes() {
        return cubes;
    }

    public String getCityName() {
        return cityName;
    }

    public String toString() {
        return cityName;
    }

    public void doInfect(String color) throws IOException {
        if (this.game.getEradiatedDiseases().contains(color)) {
            return;
        }

        if (this.game.getCubeCount(color) == 0) {
            this.game.endGame("You ran out of " + color + " cubes!");
            return;
        }

        if (this.cubes.getOrDefault(color, 0) < 3) {
            this.cubes.put(color, this.cubes.getOrDefault(color, 0) + 1);
            this.game.decrementCubeCount(color);
        } else {
            this.doOutbreak(color);
        }
    }

    public void doInfect() throws IOException {
        doInfect(color); // different colored cubes can exist
    }

    public void doOutbreak(String color) throws IOException {
        this.game.addOutbreak();
        if (this.game.getOutbreakCounter() >= 8) {
            this.game.endGame("You caused too many outbreaks... no one's alive now.");
            return;
        }

        for (City city : this.game.getCities(this)) {
            city.doInfect(color);
        }
    }
}
