package io.github.haappi.restaurant_game;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler
        implements Thread
                .UncaughtExceptionHandler { // https://www.baeldung.com/java-global-exception-handler

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.info("Unhandled exception caught!");
        // get current scene in scene builder and add a new Node to it
        StackPane pane = getMessage(e);
        HelloApplication.getInstance().getCurrentPane().getChildren().add(pane);

        // wait 5 seconds
        // remove the node from the scene
        new Thread(
                        () -> {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                            Platform.runLater(
                                    () ->
                                            HelloApplication.getInstance()
                                                    .getCurrentPane()
                                                    .getChildren()
                                                    .remove(pane));
                        })
                .start();

        // show it to the user in the current Controller view like a slide in / out animation or
        // something
        // or basically noithing if i cant figure it out in like 10 minutes.
    }

    private StackPane getMessage(Throwable exception) {
        Text message = new Text(exception.getMessage());
        Rectangle background =
                new Rectangle(
                        message.getLayoutBounds().getWidth(),
                        message.getLayoutBounds().getHeight());
        background.setFill(Color.RED);
        background.opacityProperty().set(0.5);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(background, message);

        // set the position to be the top right corner of the screen
//        stackPane.setLayoutX(
//                HelloApplication.getInstance().getStage().getWidth() - stackPane.getWidth());


        stackPane.setAlignment(Pos.TOP_RIGHT);

        return stackPane;
    }

    private void animateStackPane(StackPane stackPane) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // fixme work here
            }
        }.start();
        // todo - animate the stackpane to slide in from the top right corner of the screen
        // and then slide out after 5 seconds.

        // use an animation timer for this
    }
}
