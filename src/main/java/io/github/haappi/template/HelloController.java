package io.github.haappi.template;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class HelloController {

    public GridPane gridPane;
    public Button[][] buttons;

    @FXML
    protected void initialize() {
        int size = 10;
        buttons = new Button[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setMinSize(50, 50);
                button.setText("\uD83C\uDF3C");
                button.setTextFill(Color.GREEN);
                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }
        for (int i = 0; i < size; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < size; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(row);
        }

        Lion lion = new Lion("rawr", "Lion", 5, 0, 0, gridPane, buttons);

        new AnimationTimer() {
            long lastUpdate = 0; // it's in here and not outside because issues with lambad

            @Override
            public void handle(long now) {
                if (now - lastUpdate > 1_000_000_000) {
                    lastUpdate = now;
                    lion.move();
                }
            }
        }.start();
    }

    public void startButton(ActionEvent actionEvent) {}

    public void addSHeep(ActionEvent actionEvent) {}
}
