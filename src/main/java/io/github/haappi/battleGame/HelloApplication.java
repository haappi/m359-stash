package io.github.haappi.battleGame;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
  private static HelloApplication singleton;
  public static HelloApplication getInstance() {
    return singleton;
  }
  private Stage stage;
  @Override
  public void start(Stage stage) throws IOException {
    singleton = this;
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-menu.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
    this.stage = stage;
  }

  public void setStageScene(Scene scene) {
    stage.setScene(scene);
  }

  public void setSceneTitle(String newTitle) {
    stage.setTitle(newTitle);
  }

  public Scene getStageScene() {
    return stage.getScene();
  }

  public static void main(String[] args) {
    launch();
  }
}
