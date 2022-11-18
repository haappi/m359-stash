package io.github.haappi.wordSearchTemplate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainMenuController {
    public ListView<String> highScores;

    @FXML
    protected void initialize() {
        refreshLeader();
    }

    public void exitGame(ActionEvent event) {
        HelloApplication.setStageScene("main-menu");
    }

    public void actualGame(ActionEvent event) {
        HelloApplication.setStageScene("hello-view");
    }

    public void refreshLeader() {
        ArrayList<String> lines = Utils.readFromFileAsArray("leaderboard.txt");
        lines.remove(0);
        highScores.getItems().clear();
        ArrayList<String> sorted = Utils.sortByScore(lines);
        for (String entry : sorted) {
            highScores.getItems().add(entry.toLowerCase());
        }
    }
}
