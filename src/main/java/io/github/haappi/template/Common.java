package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class Common {

    public static Button[][] makeGridPane(int size, GridPane gridPane) {
        Button[][] buttons = new Button[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setMinSize(50, 50);
                button.setText("\uD83C\uDF3C");
                button.setStyle("-fx-background-color: #00ff00;");
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

        return buttons;
    }
}
