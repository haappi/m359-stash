package io.github.haappi.restaurant_game;

import com.google.gson.annotations.Expose;

import io.github.haappi.restaurant_game.Tiles.FloorTile;
import io.github.haappi.restaurant_game.Tiles.Tile;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hosts all variables and what not in POJO for easy MongoDB mapping.
 */
public class Game extends CustomClass {
    @Expose private final long profileCreation = System.currentTimeMillis();
    private final ArrayList<Building> ownedLocations = new ArrayList<>();
    //    @Expose(serialize = false)
    public Timer timer = new Timer();
    @Expose private long lastSave = System.currentTimeMillis();
    //     https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOf(T[],%20int)
    private Tile[][] farmTiles; // same as above^
    private Tile[][] restaurantTiles; // same as above^
    private int test = 0;

    public Game(String gameCode) {
        super(gameCode);
        farmTiles = new Tile[5][5];
        for (int i = 0; i < farmTiles.length; i++) {
            for (int j = 0; j < farmTiles[i].length; j++) {
                farmTiles[i][j] = new Tile(Color.BROWN, i, j, 60);
            }
        }

        restaurantTiles = new Tile[15][15];

        for (int i = 0; i < restaurantTiles.length; i++) {
            for (int j = 0; j < restaurantTiles[i].length; j++) {
                restaurantTiles[i][j] = new FloorTile(Color.BEIGE, i, j, 60);
            }
        }

        StackPane pane = new StackPane();
        Text panee = new Text();
        pane.setOnMouseClicked(panee.getOnMouseEntered());
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

    public Tile[][] getRestaurantTiles() {
        return restaurantTiles;
    }

    public void setRestaurantTiles(Tile[][] restaurantTiles) {
        this.restaurantTiles = restaurantTiles;
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
     *
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
