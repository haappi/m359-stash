package io.github.haappi.battleGame;

import com.google.gson.Gson;
import io.github.haappi.battleGame.Classes.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static HelloApplication singleton;
    private final ArrayList<String> opponentNames = new ArrayList<>(List.of("Ogre", "Zombie", "Spider", "Rat", "Goblin", "Witch"));
    private final Gson gsonInstance = new Gson();
    private final int width = 640;
    private final int height = 480;
    private Player playerReference;
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

    public ArrayList<String> getOpponentNames() {
        return opponentNames;
    }

    @Override
    public void start(Stage stage) throws IOException {
        singleton = this;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("character-creator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
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

    /**
     * Handles changing the Scene of the Stage for you.
     *
     * @param fileName The name of the fxml file to load.
     */
    public void setStageScene(String fileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName.replace(".fxml", "") + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            HelloApplication.getInstance().setStageScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return playerReference;
    }

    public void setPlayer(Player player) {
        this.playerReference = player;
    }
}
