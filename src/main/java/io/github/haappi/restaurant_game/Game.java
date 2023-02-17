package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

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
    private int test = 0;
    @Expose
    private Weather currentWeather = Weather.SUNNY;

    public Game(String gameCode) {
        super(gameCode);
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

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
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
