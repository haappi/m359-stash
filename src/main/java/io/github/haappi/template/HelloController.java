package io.github.haappi.template;

import static io.github.haappi.template.Utils.getRandomNumber;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class HelloController {
    public GridPane gridPane;

    private Button[][] buttons;
    private Objects[][] objects;
    private Elephant elephant;

    private boolean o = true;

    @FXML
    protected void startButton() {
        buttons = new Button[5][5];
        objects = new Objects[5][5];

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button label = new Button("*");
                label.setMinSize(5, 5);
                buttons[i][j] = label;
                gridPane.add(label, j, i);

                objects[i][j] = new Grass(i, j, label, buttons);
            }
        }
        int i = getRandomNumber(0, 4);
        int j = getRandomNumber(0, 4);
        objects[i][j] = new Elephant(i, j, buttons[i][j], buttons);
        elephant = (Elephant) objects[i][j];


        for (int k = 0; k < 5; k++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER);
            gridPane.getColumnConstraints().add(cc);
        }

        for (int k = 0; k < 5; k++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER);
            gridPane.getRowConstraints().add(rc);
        }

//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                elephant.move();
//            }
//        }.start();
    }
}
