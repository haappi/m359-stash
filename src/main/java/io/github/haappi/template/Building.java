package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Building extends Objects {
    private final Button[][] buttons;
    private final GridPane gridPane;

    public Building(Button[][] buttons, GridPane gridPane) {
        this.buttons = buttons;
        this.gridPane = gridPane;
    }

}
