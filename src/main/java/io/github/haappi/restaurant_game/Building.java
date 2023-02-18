package io.github.haappi.restaurant_game;

import static io.github.haappi.restaurant_game.Utils.stringGenerator;

import com.google.gson.annotations.Expose;

import io.github.haappi.restaurant_game.Tiles.FloorTile;
import io.github.haappi.restaurant_game.Tiles.TableTile;
import io.github.haappi.restaurant_game.Tiles.Tile;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Building {
    @Expose private final Tile[][] tiles;
    @Expose private final String buildingName = stringGenerator(5);
    @Expose private final ArrayList<RevenueTrend> trends = new ArrayList<>();
    @Expose private final ArrayList<Staff> staff = new ArrayList<>();
    private final ArrayList<TableTile> chachedTables = new ArrayList<>();
    @Expose private double rating = 5.00;
    @Expose private int buildingHealth = 100;
    @Expose private int currentLevel = 1;
    @Expose private double money = 1000;
    @Expose private int plateTier;
    @Expose private int tableTier;
    @Expose private int chairTier;
    @Expose private int customersADay;
    private RestaurantView restaurantView;

    public Building(int tileSize) {
        tiles = new Tile[tileSize][tileSize];

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new FloorTile(Color.AQUAMARINE, x, y, 50);
            }
        }

        for (int x = 0; x < 5; x++) {
            int randomX = Utils.getRandomNumber(0, tiles.length - 1);
            int randomY = Utils.getRandomNumber(0, tiles.length - 1);
            tiles[randomX][randomY] = new TableTile(Color.GRAY, randomX, randomY, 50, this);
        }
        for (int x = 0; x < 5; x++) {
            staff.add(new Staff(this));
        }
        cacheTables();
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getPlateTier() {
        return plateTier;
    }

    public void setPlateTier(int plateTier) {
        this.plateTier = plateTier;
    }

    public int getTableTier() {
        return tableTier;
    }

    public void setTableTier(int tableTier) {
        this.tableTier = tableTier;
    }

    public int getChairTier() {
        return chairTier;
    }

    public void setChairTier(int chairTier) {
        this.chairTier = chairTier;
    }

    public int getCustomersADay() {
        return customersADay;
    }

    public void setCustomersADay(int customersADay) {
        this.customersADay = customersADay;
    }

    public @Nullable TableTile closestEmptyTable() {
        if (this.chachedTables == null) {
            return null;
        }
        for (TableTile tableTile : chachedTables) {
            if (tableTile.getOccupyingParty() == null) {
                return tableTile;
            }
        }
        return null;
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

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
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

    public void setMoney(double money) {
        this.money = money;
    }

    public void addOrRemoveMoney(double money) {
        this.money += money;
    }

    public ArrayList<LocationManagerHandler> getManagers(ListView attachedTo) {
        attachedTo.getItems().clear();
        attachedTo.setOnMouseClicked(
                event -> {
                    if (event.getClickCount() == 2) {
                        LocationManagerHandler item =
                                (LocationManagerHandler)
                                        attachedTo.getSelectionModel().getSelectedItem();
                        if (item != null) {
                            Platform.runLater(item::manage);
                        }
                    }
                });

        ArrayList<LocationManagerHandler> managers = new ArrayList<>();
        managers.add(new LocationManagerHandler("View Staff", this));
        managers.add(new LocationManagerHandler("Go to Location", this));
        managers.add(new LocationManagerHandler("View Stats", this));
        managers.add(new LocationManagerHandler("Upgrade", this));
        return managers;
    }

    public String toString() {
        return this.buildingName;
    }

    public String getStats() {
        return "Rating: "
                + this.rating
                + " Money: "
                + this.money
                + " Customers a day: "
                + this.customersADay
                + " Level: "
                + this.currentLevel
                + " Health: "
                + this.buildingHealth
                + " Staff: "
                + this.staff.size()
                + " Trends: "
                + this.trends.size()
                + " Tables: "
                + this.chachedTables.size()
                + "\n\n"
                + trends;
    }

    public Staff getClosestWaiter(int currentX, int currentY) {
        Staff closestWaiter = null;
        double closestDistance = Double.MAX_VALUE;
        for (Staff waiter : staff) {
            double distance =
                    Utils.heuristic(currentX, currentY, waiter.getCurrentX(), waiter.getCurrentY());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestWaiter = waiter;
            }
        }
        return closestWaiter;
    }
}
