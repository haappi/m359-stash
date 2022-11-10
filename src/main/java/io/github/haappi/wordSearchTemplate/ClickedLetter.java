package io.github.haappi.wordSearchTemplate;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ClickedLetter {
    private final Text text;
    private final int row;
    private final int column;
    private final Paint oldColor;
    private final Rectangle rectangle;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public ClickedLetter(Text text) {
        this.text = text;
        this.row = GridPane.getRowIndex(text);
        this.column = GridPane.getColumnIndex(text);
        this.oldColor = text.getFill();
        this.rectangle = new Rectangle(text.getLayoutX(), text.getLayoutY(), 15, 15);
        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.2);

        GridPane gridPane = (GridPane) text.getParent(); // Gets what the Text is associated with.
        gridPane.add(rectangle, column, row); // Adds the rectangle to the gridPane.
    }

    public Text getText() {
        return text;
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
