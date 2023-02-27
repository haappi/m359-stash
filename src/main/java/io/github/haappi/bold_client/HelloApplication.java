package io.github.haappi.bold_client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static HelloApplication instance;
    private Stage stage;

    public static synchronized HelloApplication getInstance() {
        return instance;
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

    public void loadFxmlFile(String filename) {
        filename = filename.replace(".fxml", "") + ".fxml";
        String finalFilename = filename;
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader =
                            new FXMLLoader(HelloApplication.class.getResource(finalFilename));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 1600, 900);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                });
    }

    @Override
    public void start(Stage stage) throws IOException {
        loadFxmlFile("connect_menu.fxml");
    }
}
