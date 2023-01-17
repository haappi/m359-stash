package io.github.haappi.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    /*
    https://stackoverflow.com/questions/18430814/pathfinding-in-an-2d-array
    https://gist.github.com/manpreetdeol/83dc1203ed8cf77f8faf8d66df12efe0/
    https://plainenglish.io/blog/a-algorithm-in-python

    https://gist.github.com/jamiees2/5531924
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
