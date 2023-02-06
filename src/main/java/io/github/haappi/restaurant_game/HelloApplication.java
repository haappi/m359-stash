package io.github.haappi.restaurant_game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final Gson gson =
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    public static HelloApplication instance;
    private Stage stage;
    private AnchorPane currentPane;

    public AnchorPane getCurrentPane() {
        return currentPane;
    }

    public void setCurrentPane(AnchorPane currentPane) {
        this.currentPane = currentPane;
    }

    private final int WIDTH = 1600;
    private final int HEIGHT = 900; // this is one below 1920x1080

    public static void main(String[] args) {
        launch();
    }

    public static HelloApplication getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        this.stage = stage;
        HelloApplication.instance = this;
        setStageScene("hello-view");
        /*
        Possible useful things
        https://github.com/karakun/OpenWebStart/blob/master/documentation/faq/FAQ.adoc#how-to-run-openjfx-based-javafx-applications-with-openwebstart
        https://docs.webfx.dev/#_getting_started

        https://stackoverflow.com/questions/27958019/embed-a-javafx-application-in-a-html-webpage
        https://docs.oracle.com/javase/9/deploy/self-contained-application-packaging.htm#JSDPG583
        */
    }

    /**
     * Handles changing the Scene of the Stage for you.
     *
     * @param fileName The name of the fxml file to load.
     */
    public void setStageScene(String fileName) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(
                            HelloApplication.class.getResource(
                                    fileName.replace(".fxml", "") + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            // https://edencoding.com/javafx-scene/
            // todo maybe use that to switch between scenes
            // or maybe some sort of fade out animation
            //            scene.getRoot().upda
            HelloApplication.getInstance().setStageScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStageScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
}
