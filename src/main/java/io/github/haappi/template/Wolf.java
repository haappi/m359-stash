package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Wolf extends Objects {
    private final Color color;
    private int x;
    private int y;
    private Button button;
    private final Button[][] buttons;

    public Wolf(int x, int y, Button button, Button[][] buttons) {
        this.color = Color.RED;
        this.x = x;
        this.y = y;
        this.button = button;
        this.buttons = buttons;

        this.setStuff();
    }

    private void setStuff() {
        button.setText(this.toString());
        button.setTextFill(this.color);
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        Button temp;
        temp = this.buttons[this.x][this.y];
        this.buttons[this.x][this.y] = this.buttons[x][this.y];
        this.buttons[x][this.y] = temp;

        this.x = x;
    }

    public int getY() {
        Button temp;
        temp = this.buttons[this.x][this.y];
        this.buttons[this.x][this.y] = this.buttons[this.x][y];
        this.buttons[this.x][y] = temp;

        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void translate(int x, int y) {
        if (this.x + x > buttons.length - 1 || this.x + x < 0 || this.y + y > buttons.length - 1 || this.y + y < 0) {
            return;
        }

        Button button1 = this.buttons[GridPane.getRowIndex(button)][GridPane.getColumnIndex(button)];
        Button button2 = this.buttons[GridPane.getRowIndex(button) + x][GridPane.getColumnIndex(button) + y];

        GridPane.setColumnIndex(button1, GridPane.getColumnIndex(button) + y);
        GridPane.setRowIndex(button1, GridPane.getRowIndex(button) + x);
        GridPane.setColumnIndex(button2, GridPane.getColumnIndex(button));
        GridPane.setRowIndex(button2, GridPane.getRowIndex(button));

        this.buttons[GridPane.getRowIndex(button)][GridPane.getColumnIndex(button)] = button2;
        this.buttons[GridPane.getRowIndex(button) + x][GridPane.getColumnIndex(button) + y] = button1;


        this.x += x;
        this.y += y;
    }

    public void move() {
        boolean leftOrRight = Utils.getRandomNumber(0, 1) == 0; // true = left, false = right
        boolean upOrDown = Utils.getRandomNumber(0, 1) == 0; // true = down, false = up

        int x = leftOrRight ? -1 : 1;
        int y = upOrDown ? -1 : 1;

        this.translate(x, y);
    }

    public String toString() {
        return "\uD83D\uDC3A";
    }

    public Button getButton() {
        return button;
    }
}
