package io.github.haappi.BoardGame;

public class CityCard extends Card {
    private final City city;

    public CityCard(City city) {
        this.city = city;
    }

    public String toString() {
        return city.toString();
    }

    public City getCity() {
        return city;
    }
}
