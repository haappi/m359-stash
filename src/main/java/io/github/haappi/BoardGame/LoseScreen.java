package io.github.haappi.BoardGame;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoseScreen {
    public Label label;

    @FXML
    public void initialize() {
        label.setText("You lost because \n" + HelloApplication.loseReason);
        Platform.runLater(
                () -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                });
    }
}
