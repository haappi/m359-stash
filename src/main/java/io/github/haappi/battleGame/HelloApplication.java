package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private static HelloApplication singleton;
    private final ArrayList<String> opponentNames = new ArrayList<>(List.of("Ogre", "Zombie", "Spider", "Rat", "Goblin", "Witch"));
    private final ArrayList<BattleData> battleData = new ArrayList<>();
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

    public ArrayList<String> getOpponentNames() {
        return opponentNames;
    }

    public ArrayList<BattleData> getBattleData() {
        return this.battleData;
    }

    public void addBattleToData(BattleData battleData) {
        this.battleData.add(battleData);
    }

    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("src/main/resources/battle-data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        String content = new String(Files.readAllBytes(file.toPath())); // reads the entire file into a string
        for (String line : content.split("\n\n")) {
            ArrayList<String> data = new ArrayList<>(List.of(line.split(": ")));
            data.remove(0);
            for (String thing : data) {
                data.set(data.indexOf(thing), thing.split("\n")[0]);
            }
            String[] stringArray = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                stringArray[i] = data.get(i);
            }
            if (stringArray.length > 0) {
                this.battleData.add(new BattleData(stringArray));
            }
        }
        System.out.println(this.battleData);

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
