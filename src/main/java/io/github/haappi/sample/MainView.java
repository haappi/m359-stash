package io.github.haappi.sample;

import com.gluonhq.charm.glisten.mvc.View;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class MainView {

    public View getView() {
        try {
            View view = FXMLLoader.load(MainView.class.getResource("main.fxml"), ResourceBundle.getBundle("io.github.haappi.sample.main"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }

}
