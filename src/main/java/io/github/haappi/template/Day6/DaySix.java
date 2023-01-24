package io.github.haappi.template.Day6;

import io.github.haappi.template.DayOne.BasicAnimal;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import static io.github.haappi.template.Common.makeGridPane;
import static io.github.haappi.template.Utils.getRandomNumber;

public class DaySix {
    public GridPane gridPane;
    public Button[][] buttons;
    private BasicAnimal lion;

    @FXML
    public void initialize() {
        buttons = makeGridPane(10, gridPane);
        lion = new BasicAnimal(getRandomNumber(2, 7), getRandomNumber(2, 7), Color.RED, "\uD83D\uDC3A", buttons);

//        for (int i = 0; i < 10; i++) {
//            Button button = buttons[getRandomNumber(0, 9)][getRandomNumber(0, 9)];
//            button.setText("\uD83C\uDF0A"); // 🌊
//            button.setTextFill(Color.BLUE);
//        }
        // for loop in an outer loop around lion
        for (int i = lion.getX() - 2; i < lion.getX() + 2; i++) {
            for (int j = lion.getY() - 2; j < lion.getY() + 2; j++) {
                Button button = buttons[i][j];
                button.setText("\uD83C\uDF0A"); // 🌊
                button.setTextFill(Color.BLUE);
            }
        }

        new AnimationTimer() {
            long lastUpdate = 0; // it's in here and not outside because issues with lambad

            @Override
            public void handle(long now) {
                if (now - lastUpdate > 1_000_000_000) {
                    lastUpdate = now;

                    int xToTranslate = getRandomNumber(-1, 1);
                    int yToTranslate = getRandomNumber(-1, 1);
                    lion.setX(lion.getX() + xToTranslate, true);
                    lion.setY(lion.getY() + yToTranslate, true);
                }
            }
        }.start();
    }
}
