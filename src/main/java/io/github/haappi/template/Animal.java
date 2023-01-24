package io.github.haappi.template;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Animal {
    private final String name;
    private final String type;
    private final String emoji;
    private final int age;
    private final GridPane gridPane;
    private final Button[][] buttons;
    private final ArrayList<Node> currentPathToFollow = new ArrayList<>();
    private int row;
    private int column;
    private int currentGoalX;
    private int currentGoalY;

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

        buttons[row][column].setText(emoji);
    }

    public String getEmoji() {
        return emoji;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public void moveToGoal(int x, int y) {
        currentPathToFollow.clear();
        AStar aStar = new AStar(buttons, x, y);
        aStar.calculate(row, column);
        currentPathToFollow.addAll(aStar.getPath());
        currentGoalX = x;
        currentGoalY = y;
    }

    public void move(boolean updateOnFly) {
        if (updateOnFly) {
            moveToGoal(currentGoalX, currentGoalY);
        }
        if (currentPathToFollow.size() == 0) {
            return;
        }
        Node nextPosition = currentPathToFollow.get(0);
        System.out.println(nextPosition);
        System.out.println(this.getRow() + " " + this.getColumn());

        //         remove the animal from the old position
        buttons[row][column].setTextFill(Color.GREEN);
        buttons[row][column].setText("\uD83C\uDF3C");

        this.setRow(nextPosition.getX());
        this.setColumn(nextPosition.getY());

        // set the animal to the new position
        buttons[row][column].setText(emoji);
        buttons[row][column].setTextFill(Color.RED);

        currentPathToFollow.remove(nextPosition);
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
