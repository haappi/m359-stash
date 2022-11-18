package io.github.haappi.wordSearchTemplate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    private static void setStageScene(Scene scene) {
        stage.setScene(scene);
    }

    public static void setStageScene(String fileName) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(
                            HelloApplication.class.getResource(
                                    fileName.replace(".fxml", "") + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 980);
            HelloApplication.setStageScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<String> dictionary = Utils.readFromFileAsArray("words_alpha.txt");
        dictionary.replaceAll(
                String::toUpperCase); // Calls toUpperCase on each String in the array.
        for (String word : dictionary) {
            Utils.dictionary.put(word, word);
            Utils.permDictionary.put(word, word);
        }
        /* todo's
        * levels of difficulty
        * - easy: 10x10, 10 words. medium: 15x15, 15 words. hard: 20x20, 20 words (random numbers, can swap later)
        *
          keep track of that information into a textfile
              (name, score, difficulty)
              this persists over restarts of the program
        */
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 980);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(
                event -> {
                    HelloController.cancel_timer();
                    // also save stuff like high scores in here.
                });
        HelloApplication.stage = stage;
    }
}
