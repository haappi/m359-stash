package io.github.haappi.template;

import javafx.scene.paint.Color;

public class Elephant extends Objects {
    private final Color color;
    private int x;
    private int y;

    public Elephant(int x, int y) {
        this.color = Color.GRAY;
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
        this.x += x;
        this.y += y;
    }

    public String toString() {
        return "\uD83D\uDC18";
    }
}
