package io.github.haappi.fivechallenges;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Common {
    private Common() {
        // no instances
    }

    public static void loadFMXLView(Stage stage, String name, int width, int height) {
        name = name.replace(".fxml", "");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name + ".fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("My Application");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFMXLView(Stage stage, String name) { // overloading woo
        loadFMXLView(stage, name, 600, 600);
    }
}
