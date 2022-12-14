package io.github.haappi.BoardGame;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    final City WHITEHORSE = getCity("Whitehorse", "red");
    final City EDMONTON = getCity("Edmonton", "blue");
    final City QUEBEC = getCity("Quebec", "yellow");
    final City VANCOUVER = getCity("Vancouver", "black");

    final City SEATTLE = getCity("Seattle", "red");
    final City SAN_FRANCISCO = getCity("San Francisco", "blue");
    final City SALT_LAKE_CITY = getCity("Salt Lake City", "yellow");
    final City DENVER = getCity("Denver", "black");

    final City DALLAS = getCity("Dallas", "red");
    final City ATLANTA = getCity("Atlanta", "blue");
    final City MIAMI = getCity("Miami", "yellow");
    final City NEW_YORK_CITY = getCity("New York City", "black");

    final City CHICAGO = getCity("Chicago", "red");
    final City ONTARIO = getCity("Ontario", "blue");
    private ArrayList<ConnectedUser> connectedUsers = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>(List.of(WHITEHORSE, EDMONTON, QUEBEC, VANCOUVER, SEATTLE, SAN_FRANCISCO, SALT_LAKE_CITY, DENVER, DALLAS, ATLANTA, MIAMI, NEW_YORK_CITY, CHICAGO, ONTARIO));
    private HashMap<City, ArrayList<City>> adjacentCities = new HashMap<>();
    private final HashMap<String, Integer> cubes = new HashMap<>();
    private final ArrayList<City> researchStations = new ArrayList<>(List.of(ATLANTA));
    private ArrayList<String> curedDiseases = new ArrayList<>();
    private ArrayList<String> eradicatedDiseases = new ArrayList<>();
    private int outbreakCounter = 0;

    public int getOutbreakCounter() {
        return outbreakCounter;
    }

    public void setOutbreakCounter(int outbreakCounter) {
        this.outbreakCounter = outbreakCounter;
    }

    @FXML
    protected void initialize() {
        cubes.put("red", 24);
        cubes.put("blue", 24);
        cubes.put("yellow", 24);
        cubes.put("black", 24);


        adjacentCities.put(WHITEHORSE, new ArrayList<>(List.of(EDMONTON, VANCOUVER)));
        adjacentCities.put(EDMONTON, new ArrayList<>(List.of(VANCOUVER, SALT_LAKE_CITY, ONTARIO, QUEBEC)));
        adjacentCities.put(QUEBEC, new ArrayList<>(List.of(EDMONTON, NEW_YORK_CITY, CHICAGO, ONTARIO)));
        adjacentCities.put(VANCOUVER, new ArrayList<>(List.of(WHITEHORSE, EDMONTON, SEATTLE, SALT_LAKE_CITY)));

        adjacentCities.put(SEATTLE, new ArrayList<>(List.of(VANCOUVER, SAN_FRANCISCO, SALT_LAKE_CITY)));
        adjacentCities.put(SAN_FRANCISCO, new ArrayList<>(List.of(SEATTLE, SALT_LAKE_CITY, DENVER, DALLAS)));
        adjacentCities.put(SALT_LAKE_CITY, new ArrayList<>(List.of(SEATTLE, SAN_FRANCISCO, DENVER, ONTARIO)));
        adjacentCities.put(DENVER, new ArrayList<>(List.of(SALT_LAKE_CITY, SAN_FRANCISCO, DALLAS, CHICAGO, ONTARIO, ATLANTA)));

        adjacentCities.put(DALLAS, new ArrayList<>(List.of(CHICAGO, DENVER, ATLANTA, MIAMI)));
        adjacentCities.put(ATLANTA, new ArrayList<>(List.of(DALLAS, CHICAGO, MIAMI)));
        adjacentCities.put(MIAMI, new ArrayList<>(List.of(ATLANTA, DALLAS)));
        adjacentCities.put(NEW_YORK_CITY, new ArrayList<>(List.of(CHICAGO, QUEBEC, ATLANTA)));

        adjacentCities.put(CHICAGO, new ArrayList<>(List.of(NEW_YORK_CITY, QUEBEC, DENVER, DALLAS, ATLANTA)));
        adjacentCities.put(ONTARIO, new ArrayList<>(List.of(QUEBEC, EDMONTON, DENVER, SALT_LAKE_CITY)));
    }

    private City getCity(String name, String color) {
        return new City(this, name, color);
    }

    public ArrayList<ConnectedUser> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(ArrayList<ConnectedUser> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public HashMap<City, ArrayList<City>> getAdjacentCities() {
        return adjacentCities;
    }

    public void setAdjacentCities(HashMap<City, ArrayList<City>> adjacentCities) {
        this.adjacentCities = adjacentCities;
    }

    public ArrayList<City> getAdjacentCities(City city) {
        for (City c : adjacentCities.keySet()) {
            if (c.getCityName().equals(city.getCityName())) {
                return adjacentCities.get(c);
            }
        }
        return null;
    }

    public HashMap<String, Integer> getCubes() {
        return cubes;
    }

    public Integer getCubes(String color) {
        return cubes.get(color);
    }

    public void setCubes(String key, Integer value) {
        this.cubes.put(key, value);
    }

    public ArrayList<City> getResearchStations() {
        return researchStations;
    }

    public void addResearchStation(City city) {
        this.researchStations.add(city);
    }

    public void removeResearchStation(City city) {
        this.researchStations.remove(city);
    }

    public ArrayList<String> getCuredDiseases() {
        return curedDiseases;
    }

    public void setCuredDiseases(ArrayList<String> curedDiseases) {
        this.curedDiseases = curedDiseases;
    }

    public ArrayList<String> getEradicatedDiseases() {
        return eradicatedDiseases;
    }

    public void setEradicatedDiseases(ArrayList<String> eradicatedDiseases) {
        this.eradicatedDiseases = eradicatedDiseases;
    }

    public void checkEradication(String color) {
        if (!curedDiseases.contains(color)) {
            return;
        }
        if (cubes.get(color) != 24) {
            return;
        }
        eradicatedDiseases.add(color);

    }
}
