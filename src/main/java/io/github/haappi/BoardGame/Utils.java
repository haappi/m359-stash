package io.github.haappi.BoardGame;

import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static City getCityByName(String name, Game game) {
        return getCityByName(name, game.getCities());
    }

    public static City getCityByName(String name, ArrayList<City> cities) {
        for (City city : cities) {
            if (city.getCityName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    public static int getResearchStationCount(Game game) {
        return getResearchStationCount(game.getCities());
    }

    public static int getResearchStationCount(ArrayList<City> cities) {
        int count = 0;
        for (City city : cities) {
            if (city.isHasResearchStation()) {
                count++;
            }
        }
        return count;
    }

    public static <T> ArrayList<Object> getTextInput(String title, ListView<T> listView) {
        return getTextInput(title, listView, " to a city");
    }

    public static <T> ArrayList<Object> getTextInput(
            String title, ListView<T> listView, String appendToTitle) {
        TextInputDialog td = new TextInputDialog(title);
        td.setTitle(title);
        td.setHeaderText(title + appendToTitle);
        // https://stackoverflow.com/questions/30026824/modifying-local-variable-from-inside-lambda
        var wrapper = new Wrapper();
        listView.setPrefHeight(listView.getItems().size() * 69 + 2);
        listView.setOnMouseClicked(
                event -> {
                    if (listView.getSelectionModel().getSelectedItem() == null) {
                        return;
                    }
                    td.hide();
                    wrapper.set(listView.getSelectionModel().getSelectedItem());
                });
        td.getDialogPane().setContent(listView);
        return new ArrayList<>(List.of(td, wrapper));
    }

    /**
     * Returns a {@link FileInputStream} for a given asset in the assets' directory.
     *
     * @param fileName A {@link String} containing the filename.
     * @return The {@link FileInputStream} if the file is found, else <b><font color ="orange">null</font></b>
     */
    public static FileInputStream getImage(String fileName) {
        fileName =
                fileName.replace(" ", "_").split("\\.")[0]; // Escape the dot, get the first element
        try {
            return new FileInputStream("src/main/resources/assets/" + fileName + ".png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image fileStreamToImage(FileInputStream fileInputStream) {
        return new Image(fileInputStream);
    }
}
