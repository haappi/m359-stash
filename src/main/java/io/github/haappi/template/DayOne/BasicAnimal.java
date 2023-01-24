package io.github.haappi.template.DayOne;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class BasicAnimal {
    private final String emoji;
    private final Color color;
    private final Button[][] buttons;
    private int x;
    private int y;
    private boolean isInWater;

    public BasicAnimal(int x, int y, Color color, String emoji, Button[][] buttons) {
        this.x = x;
        this.y = y;
        this.emoji = emoji;
        this.buttons = buttons;
        this.color = color;

        buttons[x][y].setText(emoji);
    }

    public Color getColor() {
        return color;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public boolean isInWater() {
        return isInWater;
    }

    public void setInWater(boolean inWater) {
        isInWater = inWater;
    }

    public ArrayList<BasicAnimal> getNearby(int radius) {
        ArrayList<BasicAnimal> nearby = new ArrayList<>();

        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                if (i >= 0 && i < buttons.length && j >= 0 && j < buttons.length) {
                    if (!buttons[i][j].getText().equals("\uD83C\uDF3C") || buttons[i][j].getText().equals(emoji)) { // ignore grass & same type
                        nearby.add(new BasicAnimal(i, j, Color.RED, buttons[i][j].getText(), buttons));
                    }
                }
            }
        }

        return nearby;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX(int x, boolean bypass) {
        if (x < 0 || x > 9) {
            return;
        }

        if (!buttons[x][this.y].getText().equals("\uD83C\uDF3C")) { // 🌼
            if (!bypass) {
                return;
            }
        }
        if (buttons[x][this.y].getText().equals("\uD83C\uDFDB️")) { // 🏛️
            return;
        }

        isInWater = buttons[x][this.y].getText().equals("\uD83C\uDF0A"); // 🌊

        buttons[this.x][this.y].setText(isInWater ? "\uD83C\uDF0A" : "\uD83C\uDF3C"); // 🌊 🌼
        buttons[this.x][this.y].setStyle("-fx-background-color: #00ff00;");
        this.x = x;
        buttons[this.x][this.y].setText(emoji);
        buttons[this.x][this.y].setStyle("-fx-background-color: " + color.toString().substring(2) + ";");

        if (isInWater) {
            buttons[this.x][this.y].setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #0000FF, #FF0000);");
        } else {
            buttons[this.x][this.y].setStyle("-fx-background-color: #FF0000;");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY(int y, boolean bypass) {
        if (y < 0 || y > 9) {
            return;
        }

        if (!buttons[this.x][y].getText().equals("\uD83C\uDF3C")) { // 🌼
            if (!bypass) {
                return;
            }
        }
        if (buttons[x][this.y].getText().equals("\uD83C\uDFDB️")) { // 🏛️
            return;
        }


        isInWater = buttons[this.x][y].getText().equals("\uD83C\uDF0A"); // 🌊

        buttons[this.x][this.y].setText(isInWater ? "\uD83C\uDF0A" : "\uD83C\uDF3C"); // 🌊 🌼
        buttons[this.x][this.y].setStyle("-fx-background-color: #00ff00;");
        this.y = y;
        buttons[this.x][this.y].setText(emoji);
        buttons[this.x][this.y].setStyle("-fx-background-color: " + color.toString().substring(2) + ";");

        if (isInWater) {
            buttons[this.x][this.y].setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #0000FF, #FF0000);");
        } else {
            buttons[this.x][this.y].setStyle("-fx-background-color: #FF0000;");
        }
    }

    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        return this.emoji;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicAnimal other = (BasicAnimal) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return this.emoji.equals(other.emoji);
    }
}
