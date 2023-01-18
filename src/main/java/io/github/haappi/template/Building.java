package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Building extends UnmovableObject {
    private final Button[][] buttons;
    private CoordPoints topLeft;
    private CoordPoints bottomRight;
    private Color color;
    private final GridPane gridPane;
    private double rating = 1.00;
    private ArrayList<ProduceType> produceTypes = new ArrayList<>();


    public Building(Button[][] buttons, GridPane gridPane) {
        super(0, 0); //fixme correct these values later
        this.buttons = buttons;
        this.gridPane = gridPane;
    }
}
