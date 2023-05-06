package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class PrimaryView {

    public View getView() {
        try {
            return FXMLLoader.load(PrimaryView.class.getResource("primary.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
