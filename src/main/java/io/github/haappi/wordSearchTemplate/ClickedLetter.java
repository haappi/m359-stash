package io.github.haappi.wordSearchTemplate;

import static io.github.haappi.wordSearchTemplate.Utils.setRectangleStyle;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

public class ClickedLetter {
    private final Label label;
    private final int row;
    private final int column;
    private final Paint oldColor;
    private final Region region;

    public ClickedLetter(Label label, int positionInArray, Integer direction, String color) {
        this.label = label;
        this.row = GridPane.getRowIndex(this.label);
        this.column = GridPane.getColumnIndex(this.label);
        this.oldColor = this.label.getTextFill();
        this.region = new Region();
        GridPane gridPane =
                (GridPane) this.label.getParent(); // Gets what the Text is associated with.

        region.setPrefSize(label.getPrefWidth(), label.getPrefHeight());
        region.setOpacity(0.4);
        setRectangleStyle(region, color, 0, 0, 0, 0);
        switch (direction != null ? direction : -1) {
            case 0: // down
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 20, 20, 0, 0);
                }
                break;
            case 1: // up
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 0, 0, 20, 20);
                }
                break;
            case 2: // right
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 20, 0, 0, 20);
                }
                break;
            case 3: // left
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 0, 20, 20, 0);
                }
                break;
            case 4: // down right
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 20, 0, 0, 20);
                }
                region.setRotate(45);
                break;
            case 5: // down left
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 20, 20, 0, 0);
                }
                region.setRotate(45);
                break;
            case 6: // up right
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 20, 0, 0, 20);
                }
                region.setRotate(-45);
                break;
            case 7: // up left
                if (positionInArray == 0) {
                    setRectangleStyle(region, color, 0, 0, 20, 20);
                }
                region.setRotate(-45);
                break;
        }

        gridPane.getChildren().remove(this.label);
        gridPane.add(region, column, row); // Adds the region to the gridPane.
        gridPane.add(this.label, column, row); // Adds the label to the gridPane.
    }

    public ClickedLetter(Label label, int positionInArray, int direction) {
        this(label, positionInArray, direction, "red");
    }

    public Region getRegion() {
        return region;
    }

    public Label getLabel() {
        return label;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Paint getOldColor() {
        return this.oldColor;
    }

    public String toString() {
        return this.label.getText();
    }
}
