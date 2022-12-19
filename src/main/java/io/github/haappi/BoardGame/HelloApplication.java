package io.github.haappi.BoardGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final Game game;
    public static Stage stage;

    static {
        try {
            game = new Game();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setScene(String s) throws IOException {
        setScene(s, false, 600, 400);
    }

    public static void setScene(String fileName, boolean fullScreen, int width, int height)
            throws IOException {
        fileName = fileName.replace(".fxml", "") + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(fullScreen);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        setScene("hello-view", false, 600, 400);
    }
}
