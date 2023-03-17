package io.github.haappi.bold_client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameView {
    public GridPane gameBoard;
    public Label whosTurn;

    private static GameView instance;

    @FXML
    protected void initialize() {
        GameView.instance = this;
        gameBoard = new GridPane();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                gameBoard.add(new Label("X"), i, j);
            }
        }
    }

    public static GameView getInstance() {
        if (in)
    }


}
