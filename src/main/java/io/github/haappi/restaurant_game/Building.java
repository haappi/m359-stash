package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

import io.github.haappi.restaurant_game.Tiles.FloorTile;
import io.github.haappi.restaurant_game.Tiles.TableTile;
import io.github.haappi.restaurant_game.Tiles.Tile;

import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static io.github.haappi.restaurant_game.Utils.stringGenerator;

public class Building {
    @Expose private final Tile[][] tiles;

    public Tile[][] getTiles() {
        return tiles;
    }

    public String getBuildingName() {
        return buildingName;
    }

    @Expose
    private final String buildingName = stringGenerator(5);
    @Expose private final ArrayList<RevenueTrend> trends = new ArrayList<>();
    @Expose private final ArrayList<Staff> staff = new ArrayList<>();
    @Expose private double rating = 5.00;
    @Expose private int buildingHealth = 100;
    @Expose private int currentLevel = 1;
    @Expose private double money = 1000;

    public void setCustomersADay(int customersADay) {
        this.customersADay = customersADay;
    }

    @Expose private int customersADay;

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCustomersADay() {
        return customersADay;
    }

    private final ArrayList<TableTile> chachedTables = new ArrayList<>();
    @Expose private RestaurantView restaurantView;

    public Building(int tileSize) {
        tiles = new Tile[tileSize][tileSize];

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new FloorTile(Color.BROWN, x, y, 50);
            }
        }

        for (int x = 0; x < 2; x++) {
            int randomX = Utils.getRandomNumber(0, tiles.length - 1);
            int randomY = Utils.getRandomNumber(0, tiles.length - 1);
            tiles[randomX][randomY] = new TableTile(Color.GRAY, randomX, randomY, 50, this);
        }
        cacheTables();
    }

    private void cacheTables() {
        chachedTables.clear();
        for (Tile[] tile : tiles) {
            for (Tile tile1 : tile) {
                if (tile1 instanceof TableTile tableTile) {
                    chachedTables.add(tableTile);
                }
            }
        }
    }

    public int getUpgradeCost() {
        return currentLevel * 1000;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public ArrayList<Staff> addStaff(Staff staff) {
        this.staff.add(staff);
        return this.staff;
    }

    public int getBuildingHealth() {
        return buildingHealth;
    }

    public void setBuildingHealth(int buildingHealth) {
        this.buildingHealth = buildingHealth;
        if (buildingHealth <= 20) {
            HelloApplication.getInstance().getGameInstance().getOwnedLocations().remove(this);
        }
    }

    public void upgrade() {
        currentLevel++;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void addTrend(RevenueTrend trend) {
        trends.add(trend);
    }

    public ArrayList<RevenueTrend> getTrends() {
        return trends;
    }

    public void setRestaurantView(RestaurantView restaurantView) {
        if (this.restaurantView != null) {
            throw new IllegalStateException("RestaurantView already set");
        }
        this.restaurantView = restaurantView;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getMoney() {
        return money;
    }

    public void addOrRemoveMoney(double money) {
        this.money += money;
    }

    public ArrayList<LocationManagerHandler> getManagers(ListView attachedTo) {
        attachedTo.getItems().clear();
        attachedTo.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                LocationManagerHandler item = (LocationManagerHandler) attachedTo.getSelectionModel().getSelectedItem();
                item.manage();
            }
        });


        ArrayList<LocationManagerHandler> managers = new ArrayList<>();
        managers.add(new LocationManagerHandler("View Staff", this));
        managers.add(new LocationManagerHandler("Go to Location", this));
        managers.add(new LocationManagerHandler("View Stats", this));
        managers.add(new LocationManagerHandler("Upgrade", this));
        return managers;
    }
}
