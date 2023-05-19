package io.github.haappi.views;

import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import io.github.haappi.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class Pomodoro {
    private int sessionCount;
    private int remainingSessions;
    private String workingOn;
    private int remainingTime;
    private boolean isRunning;
    private boolean isPaused;
    private Timeline timeline;
    @FXML
    private Label timerLabel;
    @FXML
    private TextField sessionCountTextField;
    @FXML
    private TextField taskTextField;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("pomodoro.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }

    private void createTimerFlow(String sessionCount) {
        try {
            this.sessionCount = Integer.parseInt(sessionCount);
            this.remainingSessions = this.sessionCount;
        } catch (NumberFormatException e) {
            System.out.println("has to be minutes dingdong");
            return;
        }

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitleText("What do you want to work on during this session?");

        VBox dialogContent = new VBox();
        javafx.scene.control.TextField inputField = new javafx.scene.control.TextField();
        Button saveButton = new Button("Save");

        saveButton.setOnAction(
                event -> {
                    dialog.setResult(inputField.getText());
                    dialog.hide();
                });

        dialogContent.getChildren().addAll(new Label("Answer Here:"), inputField, saveButton);
        dialog.setContent(dialogContent);

        dialog
                .showAndWait()
                .ifPresent(
                        input -> {
                            this.workingOn = input;
                            this.remainingTime = 25 * 60; // 25 minutes
                            this.isRunning = true;
                            this.startPomodoro();
                        });
    }

    private void startPomodoro() {
        if (!isRunning) {
            sessionCount = Integer.parseInt(sessionCountTextField.getText());
            workingOn = taskTextField.getText();
            remainingTime = 25 * 60; // 25 minutes

            timeline =
                    new Timeline(
                            new KeyFrame(
                                    Duration.seconds(1),
                                    event -> {
                                        if (remainingTime > 0) {
                                            remainingTime--;
                                            updateTimer();
                                        } else {
                                            sessionCount--;
                                            if (sessionCount == 0) {
                                                stopTimer();
                                            } else {
                                                remainingTime = 5 * 60; // 5 minutes break
                                                updateTimer();
                                            }
                                        }
                                    }));

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            isRunning = true;
        }
    }

    @FXML
    private void startTimer() {
        final FloatingActionButton floatingActionButton = new FloatingActionButton();
        floatingActionButton.setOnAction(
                e -> {
                    if (this.sessionCount == 0) {
                    } else {
                        this.createTimerFlow(Integer.toString(this.sessionCount));
                    }

                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitleText("How many 25 minute sessions do you want to work?");

                    VBox dialogContent = new VBox();
                    javafx.scene.control.TextField inputField = new javafx.scene.control.TextField();
                    Button saveButton = new Button("Save");

                    saveButton.setOnAction(
                            event -> {
                                dialog.setResult(inputField.getText());
                                dialog.hide();
                            });

                    dialogContent.getChildren().addAll(new Label("Count:"), inputField, saveButton);
                    dialog.setContent(dialogContent);

                    dialog.showAndWait().ifPresent(this::createTimerFlow);
                });
    }

    @FXML
    private void pauseTimer() {
        if (isRunning && !isPaused) {
            timeline.pause();
            isPaused = true;
        }
    }

    @FXML
    private void resumeTimer() {
        if (isRunning && isPaused) {
            timeline.play();
            isPaused = false;
        }
    }

    private void stopTimer() {
        timeline.stop();
        isRunning = false;
        isPaused = false;
        remainingTime = 0;
        updateTimer();
    }

    private void updateTimer() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(time);
    }
}
