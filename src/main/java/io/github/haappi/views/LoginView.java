package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import io.github.haappi.HelloApplication;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class LoginView {

    public View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
