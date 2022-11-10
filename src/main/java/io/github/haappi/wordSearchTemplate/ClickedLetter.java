package io.github.haappi.wordSearchTemplate;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;


public class ClickedLetter {
    private final Label label;
    private final int row;
    private final int column;
    private final Paint oldColor;
    private final Region rectangle;

    public Region getRectangle() {
        return rectangle;
    }

    public ClickedLetter(Label label, int positionInArray) {
        this.label = label;
        this.row = GridPane.getRowIndex(this.label);
        this.column = GridPane.getColumnIndex(this.label);
        this.oldColor = this.label.getTextFill();
        this.rectangle = new Region();
        rectangle.setPrefSize(label.getPrefWidth(), label.getPrefHeight());
//        rectangle.setFill(Color.GOLD);
        rectangle.setOpacity(0.2);
        if (positionInArray == 0) {
            // top left, top right, bottom right, bottom left
            rectangle.setStyle("-fx-background-color: red; -fx-background-radius: 20 0 0 20;");
        }  else {
            rectangle.setStyle("-fx-background-color: red; -fx-background-radius: 0 20 20 0;");
        }
//        rectangle.setArcHeight(30);
//        rectangle.setArcWidth(30); // todo refactor this to only make two corners rounded. leave others in line intact.

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
