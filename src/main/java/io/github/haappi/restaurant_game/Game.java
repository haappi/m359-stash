package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;
import io.github.haappi.restaurant_game.Tiles.TableTile;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hosts all variables and what not in POJO for easy MongoDB mapping.
 */
public class Game extends CustomClass {
    @Expose
    private final long profileCreation = System.currentTimeMillis();
    @Expose
    private final ArrayList<Building> ownedLocations = new ArrayList<>();
    @Expose
    private long lastSave = System.currentTimeMillis();
    //     https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOf(T[],%20int)
    @Expose
    private int money = 2000;
    @Expose
    private Weather currentWeather = Weather.SUNNY;
    private Building currentBuilding;

    public Game(String gameCode) {
        super(gameCode);
    }

    public long getProfileCreation() {
        return profileCreation;
    }

    public long getLastSave() {
        return lastSave;
    }

    public void setLastSave(long lastSave) {
        this.lastSave = lastSave;
    }

    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public void startDoingPartyTask() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (ownedLocations.size() == 0) {
                            return;
                        }
                        Building randomBuilding =
                                ownedLocations.get((int) (Math.random() * ownedLocations.size()));
                        System.out.println(randomBuilding);
                        TableTile table = randomBuilding.closestEmptyTable();
                        if (table == null) {
                            return;
                        }
                        Party party = new Party(Utils.getRandomNumber(1, 4), randomBuilding.getTiles());
                        party.moveToGoal(table.getX(), table.getY());
                        party.setCurrentX(15);
                        party.setCurrentY(7);

                        randomBuilding.setCustomersADay(
                                randomBuilding.getCustomersADay() + party.getSize());
                        randomBuilding.setRating(randomBuilding.getRating() + 0.01);
                        if (randomBuilding.getCustomersADay() >= 100) {
                            randomBuilding.setCurrentLevel(randomBuilding.getCurrentLevel() + 1);
                            randomBuilding.addOrRemoveMoney(1000);
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() + 10);
                        }
                        if (randomBuilding.getBuildingHealth() >= 100) {
                            randomBuilding.setBuildingHealth(100);
                        }
                        if (randomBuilding.getRating() >= 5.00) {
                            randomBuilding.setRating(5.00);
                        }
                    }
                },
                5000,
                30000); // 30 seconds
    }

    public void startDoingWeatherTask() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        currentWeather =
                                Weather.values()[(int) (Math.random() * Weather.values().length)];
                        if (ownedLocations.size() == 0) {
                            return;
                        }
                        Building randomBuilding =
                                ownedLocations.get((int) (Math.random() * ownedLocations.size()));
                        if (currentWeather == Weather.RAINY) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 5);
                        }
                        if (currentWeather == Weather.SNOWY) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 5);
                        }
                        if (currentWeather == Weather.HAIL) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 10);
                        }
                        if (currentWeather == Weather.FOGGY) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 1);
                        }
                        if (currentWeather == Weather.CLOUDY) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 1);
                        }
                        if (currentWeather == Weather.THUNDERSTORM) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 10);
                        }
                        if (currentWeather == Weather.TORNADO) {
                            randomBuilding.setBuildingHealth(
                                    randomBuilding.getBuildingHealth() - 20);
                        }
                        if (randomBuilding.getBuildingHealth() <= 0) {
                            ownedLocations.remove(randomBuilding);
                            money += randomBuilding.getMoney() - randomBuilding.getMoney() / 2;
                            money -= 500 - randomBuilding.getRating() * 75;
                        }
                        if (money <= 100) {
                            System.out.println(
                                    "You have lost the game! You can't afford to keep your"
                                            + " restaurant open!");
                            Platform.exit();
                            System.exit(0);
                        }
                    }
                },
                0,
                1000 * 30); // 30 seconds
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public long getProfileCreationTime() {
        return profileCreation;
    }

    public long getLastSaveTime() {
        return lastSave;
    }

    public void setLastSaveTime(long time) {
        this.lastSave = time;
    }

    public ArrayList<Building> getOwnedLocations() {
        return ownedLocations;
    }

    public ArrayList<Building> addLocation(Building building) {
        ownedLocations.add(building);
        return ownedLocations;
    }

    /**
     * Attempts to save this profile asynchronously.
     */
    public void saveProfile() {
        new Thread(this::doSave).start();
    }

    /**
     * Attempts to save this profile off of the {@link Thread} this was invoked from.
     */
    public void doSave() {
        this.lastSave = System.currentTimeMillis();
        DBHandler.getInstance()
                .insert( // todo somehow inform the user in an animated way
                        this,
                        DBHandler.getInstance()
                                .getCollection(DBHandler.dbName, DBHandler.collectionName));
    }

    /**
     * Schedules auto save
     *
     * @param interval A {@link Long} stating the interval in milliseconds
     */
    public void autoSave(long interval) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        doSave();
                    }
                },
                0L,
                interval);
    }
}
