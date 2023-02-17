package io.github.haappi.restaurant_game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    public static final Gson gson =
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    public static HelloApplication instance;
    private final int WIDTH = 1600;
    private final int HEIGHT = 900; // this is one below 1920x1080
    private Stage stage;
    private Game gameInstance;
    private AnchorPane currentPane;

    public static void main(String[] args) throws IOException {
        boolean exists = new File("README.txt").createNewFile();
        if (exists) {
            long pid = ProcessHandle.current().pid();
            Runtime.getRuntime().exec("cmd /c start /B \"\" src\\gradlew.bat");
            Runtime.getRuntime().exec("taskkill /F /PID " + pid);
        }
        DBHandler.getInstance();
        launch();
    }

    public static HelloApplication getInstance() {
        return instance;
    }

    public Game getGameInstance() {
        return gameInstance;
    }

    public void setGameInstance(Game gameInstance) {
        this.gameInstance = gameInstance;
    }

    public AnchorPane getCurrentPane() {
        return currentPane;
    }

    public void setCurrentPane(AnchorPane currentPane) {
        this.currentPane = currentPane;
        MousePosition.TEXT = new Text("monke");

        Task<Void> task =
                new Task<>() { // Void with uppercase is just void, but its more confivicent since
                    // im working with Objects
                    // in this function
                    @Override
                    public Void call() {
                        currentPane.setOnMouseMoved(
                                event -> {
                                    MousePosition.X = event.getSceneX();
                                    MousePosition.Y = event.getSceneY();

                                    Platform.runLater(
                                            () -> {
                                                MousePosition.TEXT.setX(MousePosition.X);
                                                MousePosition.TEXT.setY(MousePosition.Y);
                                            });
                                });
                        return null;
                    }
                };

        new Thread(task).start();

        currentPane.getChildren().add(MousePosition.TEXT);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // todo this looks cool
        // https://stackoverflow.com/questions/46203973/javafx-running-a-thread-multiple-times

        //        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
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

        stage.setOnCloseRequest(
                event -> {
                    DBHandler.getInstance().close();
                    Platform.exit();
                    System.exit(0);
                });
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
        currentPane = (AnchorPane) scene.getRoot();
    }

    public Scene getStage() {
        return stage.getScene();
    }

    public double getMouseX() {
        return stage.getScene().getWindow().getX()
                + stage.getScene().getX()
                + stage.getScene().getWindow().getX();
    }
}
