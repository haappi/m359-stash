package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Elephant extends Objects {
    private final Color color;
    private int x;
    private int y;
    private Button button;
    private final Button[][] buttons;

    public Elephant(int x, int y, Button button, Button[][] buttons) {
        this.color = Color.GRAY;
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
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void translate(int x, int y) {
        if (this.x + x > buttons.length - 1
                || this.x + x < 0
                || this.y + y > buttons.length - 1
                || this.y + y < 0) {
            return;
        }

        Button temp;
        temp = this.buttons[this.x][this.y];
        this.buttons[this.x][this.y] = this.buttons[this.x + x][this.y + y];
        this.buttons[this.x + x][this.y + y] = temp;

        this.x += x;
        this.y += y;
        this.button = this.buttons[this.x][this.y];

        this.setStuff();
    }

    public void move() {
        boolean leftOrRight = Utils.getRandomNumber(0, 1) == 0; // true = left, false = right
        boolean upOrDown = Utils.getRandomNumber(0, 1) == 0; // true = down, false = up

        int x = leftOrRight ? -1 : 1;
        int y = upOrDown ? -1 : 1;

        this.translate(x, y);
    }

    public String toString() {
        return "\uD83D\uDC18";
    }
}
