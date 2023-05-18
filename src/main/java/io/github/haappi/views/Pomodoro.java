package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import io.github.haappi.HelloApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class Pomodoro {
    public VBox vbox;
    public View primary;
    public Label label;
    private boolean isWorkingTime = false;
    private int remainSeconds = 0;
    private Timeline timeline;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("pomodoro.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }

    private String formatTimerText(int seconds) {
        int minutes = remainSeconds / 60;
        int secondss = remainSeconds % 60;
        return (String.format("%02d:%02d", minutes, secondss));
    }


public void pause() {
    if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
        timeline.pause();
    }
}

public void continueTimer() {
    if (timeline != null && timeline.getStatus() == Animation.Status.PAUSED) {
        timeline.play();
    }
}


    public void restart(ActionEvent actionEvent) {
        startTimer();
    }

    private void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }

        if (isWorkingTime) {
            remainSeconds = 25 * 60; // 25 minutes
            label.setText("Work Time: " + formatTimerText(remainSeconds));
        } else {
            remainSeconds = 5 * 60;
            label.setText("Break Time: " + formatTimerText(remainSeconds));
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainSeconds--;

            if (remainSeconds <= 0) {
                timeline.stop();
                isWorkingTime = !isWorkingTime;
            }

            label.setText((isWorkingTime ? "Work Time: " : "Break Time: ") + formatTimerText(remainSeconds));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}
