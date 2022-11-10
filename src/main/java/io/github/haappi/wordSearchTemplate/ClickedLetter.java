package io.github.haappi.wordSearchTemplate;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


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
        this.rectangle = new Rectangle(label.getLayoutX(), label.getLayoutY(), label.getPrefWidth(), label.getPrefHeight());
        rectangle.setFill(Color.GOLD);
        rectangle.setOpacity(0.2);
//        rectangle.setStyle("-fx-fill: red; -fx-background-radius: 500 500 0 0;");
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30); // todo refactor this to only make two corners rounded. leave others in line intact.

//        rectangle.setTextAlignment(TextAlignment.CENTER);
//        rectangle.setAlignment(Pos.CENTER);
//        rectangle.setContentDisplay(ContentDisplay.CENTER);

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
