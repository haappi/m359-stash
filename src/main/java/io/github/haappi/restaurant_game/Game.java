package io.github.haappi.restaurant_game;


/**
 * Hosts all variables and what not in POJO for easy MongoDB mapping.
 */
public class Game extends CustomClass {
    //    private Tile[][]
    //            restaurantTiles; //
    // https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOf(T[],%20int)
    //    private Tile[][] farmTiles; // same as above^
    //    private final ArrayList<Building> ownedLocations = new ArrayList<>();
    private int test = 0;

    public Game(String gameCode) {
        super(gameCode);
        // else instead.
        //        restaurantTiles = new Tile[5][5];
        //        farmTiles = new Tile[5][5];
    }


//        public Tile[][] getRestaurantTiles() {
//            return restaurantTiles;
//        }
//
//        public void setRestaurantTiles(Tile[][] restaurantTiles) {
//            this.restaurantTiles = restaurantTiles;
//        }
//
//        public Tile[][] getFarmTiles() {
//            return farmTiles;
//        }
//
//        public void setFarmTiles(Tile[][] farmTiles) {
//            this.farmTiles = farmTiles;
//        }
//
//        public ArrayList<Building> getOwnedLocations() {
//            return ownedLocations;
//        }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
