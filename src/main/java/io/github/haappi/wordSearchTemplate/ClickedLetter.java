package io.github.haappi.wordSearchTemplate;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

import static io.github.haappi.wordSearchTemplate.Utils.setRectangleStyle;


public class ClickedLetter {
    private final Label label;
    private final int row;
    private final int column;
    private final Paint oldColor;
    private final Region rectangle;

    public ClickedLetter(Label label, int positionInArray, int direction) {
        this.label = label;
        this.row = GridPane.getRowIndex(this.label);
        this.column = GridPane.getColumnIndex(this.label);
        this.oldColor = this.label.getTextFill();
        this.rectangle = new Region();
        rectangle.setPrefSize(label.getPrefWidth(), label.getPrefHeight());
        rectangle.setOpacity(0.2);
        setRectangleStyle(rectangle, 0, 0, 0, 0);
        switch (direction) {// todo fix the diagonals not attaching correctly.
            case 0: // down
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 20, 20, 0, 0);
                }
                break;
            case 1: // up
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 0, 0, 20, 20);
                }
                break;
            case 2: // right
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 20, 0, 0, 20);
                }
                break;
            case 3: // left
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 0, 20, 20, 0);
                }
                break;
            case 4: // down right
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 20, 0, 0, 20);
                }
                rectangle.setRotate(45);
                break;
            case 5: // down left
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 20, 20, 0, 0);
                }
                rectangle.setRotate(45);
                break;
            case 6: // up right
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 20, 0, 0, 20);
                }
                rectangle.setRotate(-45);
                break;
            case 7: // up left
                if (positionInArray == 0) {
                    setRectangleStyle(rectangle, 0, 0, 20, 20);
                }
                rectangle.setRotate(-45);
                break;
        }

//        rectangle.setTextAlignment(TextAlignment.CENTER);
//        rectangle.setAlignment(Pos.CENTER);
//        rectangle.setContentDisplay(ContentDisplay.CENTER);

        GridPane gridPane = (GridPane) this.label.getParent(); // Gets what the Text is associated with.
        gridPane.add(rectangle, column, row); // Adds the rectangle to the gridPane.
        // todo add another rectangle in the thingabob
    }

    public Region getRectangle() {
        return rectangle;
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
