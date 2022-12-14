package io.github.haappi.BoardGame;

import java.util.ArrayList;
import java.util.HashMap;

public class City {
    private final Game gameInstance;
    private final String cityName;
    private final String color;
    private final HashMap<String, Integer> cubes = new HashMap<>();
    private boolean hasResearchStation = false;

    public City(Game gameInstance, String cityName, String color) {
        this.gameInstance = gameInstance;
        this.cityName = cityName;
        this.color = color;
        cubes.put("red", 0);
        cubes.put("blue", 0);
        cubes.put("yellow", 0);
        cubes.put("black", 0);
    }

    public boolean isHasResearchStation() {
        return hasResearchStation;
    }

    public void setHasResearchStation(boolean hasResearchStation) {
        this.hasResearchStation = hasResearchStation;
    }

    public String getCityName() {
        return cityName;
    }

    public Game getGameInstance() {
        return gameInstance;
    }

    public String getColor() {
        return color;
    }

    public HashMap<String, Integer> getCubes() {
        return cubes;
    }

    public boolean hasResearchStation() {
        return hasResearchStation;
    }

    public void setResearchStation(boolean hasResearchStation) {
        this.hasResearchStation = hasResearchStation;
    }

    public void infect() {
        this.infect(this.color);
    }

    public void infect(String color) {
        if (this.gameInstance.getEradicatedDiseases().contains(color)) {
            return;
        }

        if (this.cubes.get(color) < 3) {
            if (this.gameInstance.getCubes().get(color) == 0) {
                Utils.p(new LoseScreenPacket("Out of " + color + " cubes!"));
                return;
            }

            this.cubes.put(color, this.cubes.get(color) + 1);
            this.gameInstance.getCubes().put(color, this.gameInstance.getCubes().get(color) - 1);
        } else {

        }
    }

    public void outbreak(String color) {
        this.gameInstance.setOutbreakCounter(this.gameInstance.getOutbreakCounter() + 1);
        if (this.gameInstance.getOutbreakCounter() >= 8) {
            Utils.p(new LoseScreenPacket("Outbreak counter reached 8!"));
            return;
        }

        for (City city : this.gameInstance.getAdjacentCities(this)) {
            city.infect(color);
        }
    }

    public ArrayList<City> getAdjacentCities() {
        return this.gameInstance.getAdjacentCities(this);
    }
}
