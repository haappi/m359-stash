package io.github.haappi.template.DayThree;

import static io.github.haappi.template.Common.makeGridPane;
import static io.github.haappi.template.Utils.getRandomNumber;

import io.github.haappi.template.DayOne.BasicAnimal;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DayThree {
    private final int cash = 1000;
    public GridPane gridPane;
    public ListView buildings;
    public Button[][] buttons;
    private BasicAnimal lion;
    private int xClicked = 0;
    private int yClicked = 0;
    private String selectedBuilding = "Farm";
    private final ArrayList<BasicBuilding> buildingss = new ArrayList<>();

    @FXML
    public void initialize() {
        buildings.getItems().add("Farm");
        buildings.getItems().add("News");
        buildings.setOnMouseClicked(
                event -> {
                    selectedBuilding = buildings.getSelectionModel().getSelectedItem().toString();
                });

        buttons = makeGridPane(10, gridPane);

        for (Button[] outer : buttons) {
            for (Button button : outer) {
                button.setOnMouseClicked(
                        (event -> {
                            xClicked = GridPane.getRowIndex((Node) event.getSource());
                            yClicked = GridPane.getColumnIndex((Node) event.getSource());

                            BasicBuilding building =
                                    new BasicBuilding(
                                            xClicked,
                                            yClicked,
                                            5,
                                            Color.YELLOW,
                                            buttons,
                                            gridPane,
                                            selectedBuilding);
                            buttons[xClicked][yClicked].setText(building.toString());
                            buildingss.add(building);
                        }));
            }
        }

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

                    buildingss.forEach(BasicBuilding::produceItem);

                    for (int i = lion.getX() - 2; i <= lion.getX() + 2; i++) {
                        for (int j = lion.getY() - 2; j <= lion.getY() + 2; j++) {
                            if (i >= 0 && i < buttons.length && j >= 0 && j < buttons.length) {
                                if (!buttons[i][j].getText().equals("\uD83C\uDF3C")
                                        || buttons[i][j]
                                                .getText()
                                                .equals(lion.getEmoji())) { // ignore grass &
                                    // same type

                                    int finalI = i;
                                    int finalJ = j;
                                    buildingss.forEach(
                                            building -> {
                                                if (building.getX() == finalI
                                                        && building.getY() == finalJ) {
                                                    ProduceType type =
                                                            building.getProduceTypes().get(0);
                                                    lion.getInventory()
                                                            .put(
                                                                    type.getName(),
                                                                    lion.getInventory()
                                                                                    .getOrDefault(
                                                                                            type
                                                                                                    .getName(),
                                                                                            0)
                                                                            + type.getCount());

                                                    type.setCount(0);
                                                }
                                            });
                                }
                            }
                        }
                    }
                    System.out.println(lion.getInventory());
                }
            }
        }.start();
    }
}
