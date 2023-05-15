package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginHandler {
    public PasswordField passwordField;
    public TextField emailField;
    public TextField username;

    public static View load() {
        try {
            return FXMLLoader.load(PrimaryView.class.getResource("login.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }

    public void login(ActionEvent actionEvent) {
    }
}
