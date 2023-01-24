package io.github.haappi.template.Day2;

import io.github.haappi.template.DayOne.BasicAnimal;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static io.github.haappi.template.Common.makeGridPane;
import static io.github.haappi.template.Utils.getRandomNumber;

public class DayTwo {
    private final ArrayList<BasicAnimal> pray = new ArrayList<>();
    public GridPane gridPane;
    public Button[][] buttons;
    private BasicAnimal lion;

    @FXML
    public void initialize() {
        buttons = makeGridPane(10, gridPane);
        lion =
                new BasicAnimal(
                        getRandomNumber(0, 9),
                        getRandomNumber(0, 9),
                        Color.RED,
                        "\uD83D\uDC3A",
                        buttons);

        for (int i = 0; i < 10; i++) {
            pray.add(
                    new BasicAnimal(
                            getRandomNumber(0, 9),
                            getRandomNumber(0, 9),
                            Color.BROWN,
                            "\uD83D\uDC3B",
                            buttons)); // 🐻
        }

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

                    for (BasicAnimal animal : pray) {
                        int xToTranslatePray = getRandomNumber(-1, 1);
                        int yToTranslatePray = getRandomNumber(-1, 1);
                        animal.setX(animal.getX() + xToTranslatePray, false);
                        animal.setY(animal.getY() + yToTranslatePray, false);
                    }

                    ArrayList<BasicAnimal> nearby = lion.getNearby(1);
                    for (BasicAnimal animal : nearby) {
                        if (Math.random() < 0.5) {
                            System.out.println("Lion is hungry.");
                            lion.setX(animal.getX(), true);
                            lion.setY(animal.getY(), true);

                            for (int i = 0; i < pray.size(); i++) {
                                if (pray.get(i).getX() == animal.getX()
                                        && pray.get(i).getY() == animal.getY()) {
                                    pray.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }.start();
    }
}
