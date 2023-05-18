package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import io.github.haappi.HelloApplication;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Pomodoro {
    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("pomodoro.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }


}
