package io.github.haappi.template.DayThree;

import static io.github.haappi.template.Common.makeGridPane;
import static io.github.haappi.template.Utils.getRandomNumber;

import io.github.haappi.template.DayOne.BasicAnimal;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class DayThree {
    public GridPane gridPane;
    public ListView buildings;

    public Button[][] buttons;
    private BasicAnimal lion;
    private final int cash = 1000;

    @FXML
    public void initialize() {
        buildings.getItems().add("House");
        buildings.setOnMouseClicked(
                event -> {
                    System.out.println(buildings.getSelectionModel().getSelectedItem());
                });

        buttons = makeGridPane(10, gridPane);
        lion =
                new BasicAnimal(
                        getRandomNumber(0, 9),
                        getRandomNumber(0, 9),
                        Color.RED,
                        "\uD83D\uDC3A",
                        buttons);

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
