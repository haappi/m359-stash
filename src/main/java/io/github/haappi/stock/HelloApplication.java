package io.github.haappi.stock;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static io.github.haappi.stock.Utils.getRandomPrice;

public class HelloApplication extends Application {
  public static Stock stockOne = new Stock("Not A Space Agency", getRandomPrice(0.00, 60.00));
    public static Stock stockTwo = new Stock("Identified Flying Objects", getRandomPrice(0.00, 60.00));
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }
}
