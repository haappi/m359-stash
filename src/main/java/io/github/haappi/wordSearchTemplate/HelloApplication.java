package io.github.haappi.wordSearchTemplate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        /* todo's
         * make grid work with drag and drop
         * make validation on the fly with dragging
         * levels of difficulty
         * - easy: 10x10, 10 words. medium: 15x15, 15 words. hard: 20x20, 20 words (random numbers, can swap later)
         * words are loadwed from a file
         * timer to see how elapsed time
         *

         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
