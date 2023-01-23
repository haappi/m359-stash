package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Building extends UnmovableObject {
    private final Button[][] buttons;
    private final GridPane gridPane;
    private Color color;
    private final double rating = 1.00;
    private final ArrayList<ProduceType> produceTypes = new ArrayList<>();

    public Building(int x, int y, Button[][] buttons, GridPane gridPane) {
        super(x, y); // fixme correct these values later
        this.buttons = buttons;
        this.gridPane = gridPane;

        buttons[x][y].setText("\uD83C\uDFDF️");
    }
}
