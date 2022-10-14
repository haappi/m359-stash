package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("another.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//    Stage stage = new Stage();
//    stage.setTitle("aaaaa");
//    stage.setScene(scene);
//    stage.show();
//   Optional<Window> open = Stage.getWindows().stream().filter(Window::isShowing).findFirst();
//    if (open.isPresent()) {
//      open.get().hide();
//      open.get().setSc
//    }
//
//    ((Node)(event.getSource())).getScene().getWindow().
        HelloApplication.getInstance().setStageScene(scene); // https://stackoverflow.com/a/43179228
        // A singleton is a class that can have only one object (an instance of the class) at a time.
        // Trying to instantiate another object of the singleton class will result in a reference to the existing object.
        HelloApplication.getInstance().setSceneTitle(String.valueOf(System.currentTimeMillis()));
        // ^^ Or I can just do scuffed stuff like this.


    }
}