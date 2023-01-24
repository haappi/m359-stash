package io.github.haappi.template.DayOne;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import static io.github.haappi.template.Common.makeGridPane;
import static io.github.haappi.template.Utils.getRandomNumber;

public class DayOne {
    public GridPane gridPane;
    public Button[][] buttons;
    private BasicAnimal lion;


    @FXML
    public void initialize() {
        buttons = makeGridPane(10, gridPane);
        lion = new BasicAnimal(getRandomNumber(0, 9), getRandomNumber(0, 9), Color.RED, "\uD83D\uDC3A", buttons);

        new AnimationTimer() {
            long lastUpdate = 0; // it's in here and not outside because issues with lambad

            @Override
            public void handle(long now) {
                if (now - lastUpdate > 1_000_000_000) {
                    lastUpdate = now;

                    int xToTranslate = getRandomNumber(-1, 1);
                    int yToTranslate = getRandomNumber(-1, 1);
                    lion.setX(lion.getX() + xToTranslate, false);
                    lion.setY(lion.getY() + yToTranslate, false);
                }
            }
        }.start();
    }
}
