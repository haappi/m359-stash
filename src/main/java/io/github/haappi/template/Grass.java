package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Grass extends Objects {
    private final Color color;
    private int x;
    private int y;
    private final Button button;
    private final Button[][] buttons;


    public Grass(int x, int y, Button button, Button[][] buttons) {
        this.color = Color.GREEN;
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
        if (this.x + x > buttons.length - 1 || this.x + x < 0 || this.y + y > buttons.length - 1 || this.y + y < 0) {
            return;
        }


        Button temp;
        temp = this.buttons[this.x][this.y];
        this.buttons[this.x][this.y] = this.buttons[this.x + x][this.y + y];
        this.buttons[this.x + x][this.y + y] = temp;


        this.x += x;
        this.y += y;

        this.setStuff();
    }

    public String toString() {
        return "\uD83C\uDF3F";
    }
}
