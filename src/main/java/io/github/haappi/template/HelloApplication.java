package io.github.haappi.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    /*
    start with 2d array dynamically created grid of Button type
    put a square on the screen to represent something that moves around
    - it moves randomly every half a second

    put another square on the thing that moves indepently of the top
    - they might be able to interact with each other (if they get within a certain distance of each other)

    add differnert types of object (an elephant objcet, and maybe a grass object)

     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
