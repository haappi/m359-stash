package io.github.haappi.BoardGame;

public class CityCard extends Card {
    private final String cityName;

    public CityCard(String cityName) {
        this.cityName = cityName;
    }

    public CityCard(City city) {
        this.cityName = city.getCityName();
    }

    public String getCityName() {
        return cityName;
    }

    public String toString() {
        return cityName;
    }
}
