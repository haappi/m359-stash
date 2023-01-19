package io.github.haappi.template;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class Cell {
    private final Button[][] buttons;
    private final Button button;

    public Cell(Button[][] buttons, Button button) {
        this.buttons = buttons;
        this.button = button;
    }

    public ArrayList<Button> getNeighbors() {

    }

    private boolean isInGrid(Button button) {
        int row = Integer.MIN_VALUE;
        int col = Integer.MIN_VALUE;

        Button[][] buttons = this.buttons.clone();
        for (Button[] b : buttons) {
            for (Button innerB: b) {
                if (innerB == button) {

                }
            }
        }
    }
}
