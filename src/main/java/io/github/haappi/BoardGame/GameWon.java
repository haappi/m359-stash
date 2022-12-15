package io.github.haappi.BoardGame;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class GameWon {
    public ImageView imageView;

    @FXML
    public void initialize() {
        imageView.setImage(Utils.fileStreamToImage(Utils.getImage("game--won.png")));
        new Timer()
                .schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> Platform.exit());
                            }
                        },
                        5000);
    }
}
