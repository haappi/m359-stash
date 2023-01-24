package io.github.haappi.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application {
    public static HashMap<String, String> days = new HashMap<>();
    private static Stage stage;

    public static void setStage(String fileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /*
    https://stackoverflow.com/questions/18430814/pathfinding-in-an-2d-array
    https://gist.github.com/manpreetdeol/83dc1203ed8cf77f8faf8d66df12efe0/
    https://plainenglish.io/blog/a-algorithm-in-python

    https://gist.github.com/jamiees2/5531924
         */
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;

        HelloApplication.days.put("Day 1", "day-one.fxml");
        HelloApplication.days.put("Day 2", "day-two.fxml");
        HelloApplication.days.put("Day 3", "day-three.fxml");
        HelloApplication.days.put("Day 4", "day-four.fxml");
        HelloApplication.days.put("Day 5", "day-five.fxml");
        HelloApplication.days.put("Day 6", "day-six.fxml");

        setStage("hello-view.fxml");
    }
}
