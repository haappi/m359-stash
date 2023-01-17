package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Animal {
    private final String name;
    private final String type;
    private final String emoji;
    private final int age;
    private int row;
    private int column;
    private final GridPane gridPane;
    private final Button[][] buttons;

    public String getEmoji() {
        return emoji;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public Animal(
            String name,
            String type,
            int age,
            int row,
            int column,
            String emoji,
            GridPane gridPane,
            Button[][] buttons) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.row = row;
        this.column = column;
        this.emoji = emoji;
        this.gridPane = gridPane;
        this.buttons = buttons;
    }

    private void translate(int x, int y) {
        // check if the new position is valid
        if (row + y >= 0
                && row + y < buttons.length
                && column + x >= 0
                && column + x < buttons[0].length) {
            // remove the animal from the old position
            buttons[row][column].setText("\uD83C\uDF3C");
            buttons[row][column].setTextFill(Color.GREEN);
            // set the new position
            row += y;
            column += x;
            // set the animal to the new position
            buttons[row][column].setText(emoji);
            buttons[row][column].setTextFill(Color.RED);
        }
    }

    public void move() {
        int x = Utils.getRandomNumber(-1, 1);
        int y = Utils.getRandomNumber(-1, 1);
        translate(x, y);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return this.emoji;
    }
}
