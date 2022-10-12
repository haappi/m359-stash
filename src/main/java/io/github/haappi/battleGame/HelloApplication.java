package io.github.haappi.battleGame;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static HelloApplication singleton;
    private final Gson gsonInstance = new Gson();
    private Stage stage;

    public static HelloApplication getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        launch();
    }

    public Gson getGsonInstance() {
        return gsonInstance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        singleton = this;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public void setSceneTitle(String newTitle) {
        stage.setTitle(newTitle);
    }

    public Scene getStageScene() {
        return stage.getScene();
    }

    public void setStageScene(Scene scene) {
        stage.setScene(scene);
    }
}
