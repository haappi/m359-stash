package io.github.haappi.restaurant_game;

import javafx.application.Platform;

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
        HelloApplication.getInstance().getGameInstance().setCurrentBuilding(building);

        switch (name) {
            case "View Staff": {
                System.out.println("View Staff");
                Platform.runLater(() -> HelloApplication.getInstance().setStageScene("staff-viewer"));
                break;
            }
            case "Go to Location": {
                System.out.println("Go to Location");
                Platform.runLater(() -> HelloApplication.getInstance().setStageScene("restaurant-view"));
                break;
            }
            case "View Stats": {
                System.out.println("View Stats");
                Platform.runLater(() -> HelloApplication.getInstance().setStageScene("stats-viewer"));
                break;
            }
            case "Upgrade": {
                System.out.println("Upgrade");
                Platform.runLater(() -> HelloApplication.getInstance().setStageScene("upgrade-viewr"));
                break;
            }
        }

    }
}
