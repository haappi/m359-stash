package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Lion extends Animal {
    public Lion(
            String name,
            String type,
            int age,
            int row,
            int column,
            GridPane gridPane,
            Button[][] buttons) {
        super(name, type, age, row, column, "🦁", gridPane, buttons);
    }

    // todo implemenmt this to work with the A* algorithm
}
