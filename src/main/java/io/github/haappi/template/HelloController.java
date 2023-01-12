package io.github.haappi.template;

import static io.github.haappi.template.Utils.getRandomNumber;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

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


        for (int k = 0; k < buttons.length; k++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER);
            gridPane.getColumnConstraints().add(cc);
        }

        for (int k = 0; k < buttons[0].length; k++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER);
            gridPane.getRowConstraints().add(rc);
        }

        AnimationTimer timer ;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                elephant.move();
                System.out.println(elephant.getX() + " " + elephant.getY());
                pauseTimerForDuration(this, Duration.millis(10));
//                PauseTransition pt = new PauseTransition(Duration.seconds(1));
//                pt.setOnFinished(event -> timer.start());
//                timer.stop();
//                pt.play();
//                // this looks cool https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
//
//                // and this for custom class https://edencoding.com/animation-timer-pausing/
            }
        };
        timer.start();
    }

    void pauseTimerForDuration(AnimationTimer timer, Duration duration) {
        // https://stackoverflow.com/questions/56334617/how-to-pause-animationtimer-for-a-set-number-of-seconds

        // i would try doing this in the segement above, but the variable really wont be initalized hence it wont work
        PauseTransition pt = new PauseTransition(duration);
        pt.setOnFinished(event -> timer.start());

        timer.stop();
        pt.play();
    }
}
