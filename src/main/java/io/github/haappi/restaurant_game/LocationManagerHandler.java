package io.github.haappi.restaurant_game;

public class LocationManagerHandler {

    private final String name;
    private final Building building;

    public LocationManagerHandler(String name, Building building) {
        this.name = name;
        this.building = building;
    }

    public String toString() {
        return name;
    }

    public Building getBuilding() {
        return building;
    }

    public void manage() {
        switch (name) {
            case "View Staff":
                {
                    System.out.println("View Staff");
                    break;
                }
            case "Go to Location":
                {
                    System.out.println("Go to Location");
                    HelloApplication.getInstance().getGameInstance().setCurrentBuilding(building);
                    HelloApplication.getInstance().setStageScene("restaurant-view");
                    break;
                }
            case "View Stats":
                {
                    System.out.println("View Stats");
                    break;
                }
            case "Upgrade":
                {
                    System.out.println("Upgrade");
                    break;
                }
        }
    }
}
