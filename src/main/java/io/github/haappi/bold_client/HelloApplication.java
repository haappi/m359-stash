package io.github.haappi.bold_client;

import io.github.haappi.packets.Card;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application {
    private static HelloApplication instance;
    private Stage stage;
    public static final HashMap<String, Card> allCards = new HashMap<>();

    // I'm using a conccurrent hashmap because i may have multiple threads trying to access the
    // image at a given time, and I don't want bad things to happen

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
        instance = this;
        String imageDir = "src/main/resources/card-images";

        File dir = new File(imageDir);

        for (File f : dir.listFiles()) {
            String uri = f.toURI().toString();
            if (uri.endsWith(".png")) {
//                Card.getImage(f.toURI().toString());
                allCards.put(f.getName().replace(".png", ""), new Card(f.toURI().toString()));
            }
        }
        System.out.println(allCards);
        this.stage = stage;
        loadFxmlFile("connect-menu.fxml");
    }
}
