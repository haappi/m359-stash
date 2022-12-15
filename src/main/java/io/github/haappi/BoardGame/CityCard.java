package io.github.haappi.BoardGame;

public class CityCard extends Card {
    private final String cityName;
    private final City city;

    public CityCard(City city) {
        super(city.getCityName());
        this.cityName = city.getCityName();
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public String getCityName() {
        return cityName;
    }

    public String toString() {
        return cityName;
    }
}
