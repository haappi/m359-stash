package io.github.haappi.arraysProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;

public class HelloApplication extends Application {
    public static final HashMap<String, String> dictionary = new HashMap<>(); // This is basically a literal dictionary. It has a value paired to every "definition" (key). Duplicate keys are not allowed (Just overwrites it).
    public static final HashSet<String> dictionarySet = new HashSet<>(); // A HashSet is basically a Set, which is a Collection that doesn't allow duplicates. It's faster than a List, but ordering of items isn't saved.
    public static final HashSet<Integer> validInputTypes = new HashSet<>();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        validInputTypes.add(2);
        validInputTypes.add(3);
        validInputTypes.add(4);
        validInputTypes.add(5);
        validInputTypes.add(6);
        validInputTypes.add(7);
        validInputTypes.add(8);
        for (String line : Files.readAllLines(Utils.getResourcePath("dictionary.txt"))) {
            String[] split = line.split(":");
            if (split.length == 2) {
                dictionary.put(split[0], split[1]); // Name : Definition
                dictionarySet.add(split[0]); // Name
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
