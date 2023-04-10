package io.github.haappi.template;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class PomodoroHandler {

    private int minutes = 25;
    private int seconds = 0;
    private int pomodors = 4;
    private int completedCount = 0;
    private boolean isBreakTime = false;
    private boolean isPaused = true;

    private Label timeLabel;
    private Label statusLabel;
    private TextField pomodorosField;

    Button startButton = new Button("Start");
    Button pauseButton = new Button("Pause");

    private Timeline timeline;

    @FXML
    protected void initialize() {
        startButton.setOnAction(e -> {
            if (isPaused) {
                isPaused = false;
                startButton.setText("Resume");
            } else {
                isPaused = true;
                startButton.setText("Start");
            }
        });

        pauseButton.setOnAction(e -> {
            if (isPaused) {
                isPaused = false;
                pauseButton.setText("Resume");
            } else {
                isPaused = true;
                pauseButton.setText("Pause");
            }
        });

        createTimeline();
        timeline.play();
    }


//    public PomodoroHandler(Label timeLabel, Label statusLabel, TextField pomodorosField) {
//        this.timeLabel = timeLabel;
//        this.statusLabel = statusLabel;
//        this.pomodorosField = pomodorosField;
//
//
//    }


    private void createTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isPaused) {
                    if (seconds > 0) {
                        seconds--;
                    } else if (minutes > 0) {
                        minutes--;
                        seconds = 59;
                    } else {
                        if (isBreakTime) {
                            completedCount++;
                            if (completedCount == pomodors) {
                                statusLabel.setText("Done!");
                                timeline.stop();
                            } else {
                                statusLabel.setText("Pomodoro #" + (completedCount + 1));
                                minutes = 25;
                                seconds = 0;
                                isBreakTime = false;
                            }
                        } else {
                            statusLabel.setText("Take a break");
                            minutes = 5;
                            seconds = 0;
                            isBreakTime = true;
                        }
                    }

                    timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void resetTimer() {
        minutes = 25;
        seconds = 0;
        isBreakTime = false;
        isPaused = true;
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
        statusLabel.setText("Pomodoro #" + (completedCount + 1));
    }
}
