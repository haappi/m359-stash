package io.github.haappi.restaurant_game;

import java.io.IOException;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
  public static final Gson gson = new Gson();
  @Override
  public void start(Stage stage) throws IOException {
    /*
    Possible useful things
    https://github.com/karakun/OpenWebStart/blob/master/documentation/faq/FAQ.adoc#how-to-run-openjfx-based-javafx-applications-with-openwebstart
    https://docs.webfx.dev/#_getting_started

    https://stackoverflow.com/questions/27958019/embed-a-javafx-application-in-a-html-webpage
    https://docs.oracle.com/javase/9/deploy/self-contained-application-packaging.htm#JSDPG583
     */
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
