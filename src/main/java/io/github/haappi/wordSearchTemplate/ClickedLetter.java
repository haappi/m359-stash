package io.github.haappi.wordSearchTemplate;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ClickedLetter {
    private final Label label;
    private final int row;
    private final int column;
    private final Paint oldColor;
    private final Rectangle rectangle;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public ClickedLetter(Label label) {
        this.label = label;
        this.row = GridPane.getRowIndex(this.label);
        this.column = GridPane.getColumnIndex(this.label);
        this.oldColor = this.label.getTextFill();
        this.rectangle = new Rectangle(this.label.getLayoutX(), this.label.getLayoutY(), 19, 19);
        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.2);

        GridPane gridPane = (GridPane) this.label.getParent(); // Gets what the Text is associated with.
        gridPane.add(rectangle, column, row); // Adds the rectangle to the gridPane.
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

}
