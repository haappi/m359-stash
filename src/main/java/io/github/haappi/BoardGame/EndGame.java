package io.github.haappi.BoardGame;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EndGame {
    public static String reason = "The game ended because bad!";
    @FXML
    protected Text text;


    @FXML
    protected void initialize() {
        text.setText(reason);
    }

}
