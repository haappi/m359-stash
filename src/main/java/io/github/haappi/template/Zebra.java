package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Zebra extends Animal {
    public Zebra(
            String name,
            String type,
            int age,
            int row,
            int column,
            GridPane gridPane,
            Button[][] buttons) {
        super(name, type, age, row, column, "🦓", gridPane, buttons);
    }
}
