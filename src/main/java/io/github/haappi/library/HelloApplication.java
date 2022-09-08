package io.github.haappi.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    public static final List<String> bookNames = new ArrayList<>();
    public static final List<String> authorNames = new ArrayList<>();
    public static final List<String> genreNames = List.of("Fiction", "Non-Fiction", "Fantasy", "Graphic Novel", "Romance", "Horror", "Science Fiction", "Biography", "History", "Poetry", "Drama", "Travel", "Children's", "Religion", "Mystery", "Thriller", "Crime");

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        bookNames.addAll(Files.readAllLines(Utils.getResourcePath("book_names.txt")));
        authorNames.addAll(Files.readAllLines(Utils.getResourcePath("author_names.txt")));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


}
