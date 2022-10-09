package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HelloController {
  @FXML private Label welcomeText;

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
    HelloApplication.stage.setScene(scene); // https://stackoverflow.com/a/43179228
    // ^^ Or I can just do scuffed stuff like this.


  }
}
