package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hosts all variables and what not in POJO for easy MongoDB mapping.
 */
public class Game extends CustomClass {
    @Expose private final long profileCreation = System.currentTimeMillis();
    @Expose private long lastSave = System.currentTimeMillis();

//    @Expose(serialize = false)
    public Timer timer = new Timer();

    public long getProfileCreationTime() {
        return profileCreation;
    }

    public long getLastSaveTime() {
        return lastSave;
    }

    public void setLastSaveTime(long time) {
        this.lastSave = time;
    }

    //     https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOf(T[],%20int)
    private Tile[][] farmTiles; // same as above^
    private final ArrayList<Building> ownedLocations = new ArrayList<>();
    private int test = 0;

    public Game(String gameCode) {
        super(gameCode);
        farmTiles = new Tile[5][5];
    }

    public Tile[][] getFarmTiles() {
        return farmTiles;
    }

    public void setFarmTiles(Tile[][] farmTiles) {
        this.farmTiles = farmTiles;
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
        //            HelloApplication.getInstance().
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
     * @param interval A {@link Long} stating the interval in milliseconds
     */
    public void autoSave(long interval) {
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
