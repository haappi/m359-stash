package io.github.haappi.template;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;

public class HelloController {

    public GridPane gridPane;
    public Button[][] buttons;
    public ListView<String> listViewThingy;
    private Lion lion;
    private Building building;
    private AnimationTimer timer;

    @FXML
    protected void initialize() {
        listViewThingy.getItems().addAll(HelloApplication.days.keySet());

        int size = 10;
        buttons = new Button[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setMinSize(50, 50);
                button.setText("\uD83C\uDF3C");
                button.setStyle("-fx-background-color: #00ff00;");
                if (j == 3) {
                    if (Utils.getRandomNumber(0, 8) > 5) {
                        button.setStyle("-fx-background-color: #ff0000;");
                    }
                }
                //                button.setStyle(Utils.getRandomNumber(0, 5) < 4 ?
                // "-fx-background-color: #00ff00" : "-fx-background-color: #ff0000");
                gridPane.add(button, j, i);
                buttons[i][j] = button;

                button.setOnMouseClicked(
                        event -> {
                            if (button.getStyle().equals("-fx-background-color:#00ff00;")) {
                                button.setStyle("-fx-background-color: #ff0000;");
                            } else if (button.getStyle().equals("-fx-background-color:#0000ff;")) {
                                button.setStyle("-fx-background-color: #00ff00;");
                            } else {
                                button.setStyle("-fx-background-color: #0000ff;");
                            }
                        });
            }
        }
        for (int i = 0; i < size; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < size; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(row);
        }

        lion = new Lion("rawr", "Lion", 5, 0, 0, gridPane, buttons);

        //        AStar aStar = new AStar(buttons, 9, 9);
        //
        //        aStar.calculate(2, 4);
        //        System.out.println(aStar.getPath());

        //        buttons[9][9].setStyle("-fx-background-color: #0000ff;");
        //        buttons[2][4].setStyle("-fx-background-color: #0000ff;");
        timer =
                new AnimationTimer() {
                    long lastUpdate = 0; // it's in here and not outside because issues with

                    @Override
                    public void handle(long now) {
                        if (now - lastUpdate > 1_000_000_000) {
                            lastUpdate = now;
                            System.out.println("a");
                            lion.move(true);
                        }
                    }
                };
        timer.start();
    }

    public void startButton(ActionEvent actionEvent) {}

    public void addSHeep(ActionEvent actionEvent) {
        building =
                new Building(
                        Utils.getRandomNumber(0, 9),
                        Utils.getRandomNumber(0, 9),
                        buttons,
                        gridPane);
        lion.moveToGoal(building.getX(), building.getY());
    }

    public void aStar(ActionEvent actionEvent) {
        Button start = null;
        Button end = null;

        for (Button[] button : buttons) {
            for (Button value : button) {
                if (value.getStyle().equals("-fx-background-color: #0000ff;")) {
                    if (start == null) {
                        start = value;
                    } else {
                        end = value;
                    }
                }
            }
        }

        AStar aStar = new AStar(buttons, GridPane.getRowIndex(end), GridPane.getColumnIndex(end));
        aStar.calculate(GridPane.getRowIndex(start), GridPane.getColumnIndex(start));
        aStar.getPath()
                .forEach(
                        coordPoint -> {
                            if (buttons[coordPoint.getX()][coordPoint.getY()]
                                    .getStyle()
                                    .equals("-fx-background-color: #00ff00;")) {
                                buttons[coordPoint.getX()][coordPoint.getY()].setStyle(
                                        "-fx-background-color: #98fb98;");
                            }
                        });
    }

    public void changeDay(MouseEvent mouseEvent) throws IOException {
        timer.stop();
        String day = listViewThingy.getSelectionModel().getSelectedItem();
        HelloApplication.setStage(HelloApplication.days.get(day));
    }
}
