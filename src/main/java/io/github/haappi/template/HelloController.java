package io.github.haappi.template;

import static io.github.haappi.template.Utils.getRandomNumber;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class HelloController {
    public GridPane gridPane;

    private Button[][] buttons;
    private Objects[][] objects;
    private Wolf wolf;

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
        objects[i][j] = new Wolf(i, j, buttons[i][j], buttons);
        wolf = (Wolf) objects[i][j];

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

        AnimationTimer timer =
                new AnimationTimer() {
                    long lastUpdate = 0; // it's in here and not outside because issues with lambad

                    @Override
                    public void handle(long now) {
                        if (now - lastUpdate > 1_000_000_000) {
                            lastUpdate = now;
                            wolf.move();
                            System.out.println(wolf.getX() + " " + wolf.getY());
                            int x = GridPane.getColumnIndex(wolf.getButton());
                            int y = GridPane.getRowIndex(wolf.getButton());
                            System.out.println(x + " " + y);
                            //                    gridPane.getChildren().clear();
                            //                    for (int i = 0; i < buttons.length; i++) {
                            //                        for (int j = 0; j < buttons[i].length; j++) {
                            //                            gridPane.add(buttons[i][j], j, i);
                            //                        }
                            //                    }
                            //                    elephant.move();
                        }

                        //                try {
                        //                    Thread.sleep(1000);
                        //                } catch (InterruptedException e) {
                        //                    throw new RuntimeException(e);
                        //                }
                        //                System.out.println(Thread.currentThread().getName());
                        //                PauseTransition pt = new
                        // PauseTransition(Duration.seconds(1));
                        //                pt.setOnFinished(event -> timer.start());
                        //                timer.stop();
                        //                pt.play();
                        //                // this looks cool
                        // https://stackoverflow.com/questions/30146560/how-to-change-animationtimer-speed
                        //
                        //                // and this for custom class
                        // https://edencoding.com/animation-timer-pausing/
                    }
                };
        timer.start();
        //        System.out.println(Thread.currentThread().getName());
    }

    void pauseTimerForDuration(AnimationTimer timer, Duration duration) {
        // https://stackoverflow.com/questions/56334617/how-to-pause-animationtimer-for-a-set-number-of-seconds

        // i would try doing this in the segement above, but the variable really wont be initalized
        // hence it wont work
        PauseTransition pt = new PauseTransition(duration);
        pt.setOnFinished(event -> timer.start());

        timer.stop();
        pt.play();
    }
}
