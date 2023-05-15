package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class PrimaryView {

    public static View getView() {
        try {
            return FXMLLoader.load(PrimaryView.class.getResource("primary.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
