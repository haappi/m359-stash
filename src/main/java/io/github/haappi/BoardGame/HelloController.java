package io.github.haappi.BoardGame;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

import static io.github.haappi.BoardGame.HelloApplication.game;

public class HelloController {
    public TextField playerOneName;
    public TextField playerTwoName;

    public HelloController() throws IOException {
    }

    public void gameStart() throws IOException {
        if (playerOneName.getText() == null || playerOneName.getText().equals("")) {
            return;
        }
        if (playerTwoName.getText() == null || playerTwoName.getText().equals("")) {
            return;
        }
        game.addPlayer(new Player(game, playerOneName.getText(), Color.BLUE));
        game.addPlayer(new Player(game, playerTwoName.getText(), Color.GREEN));

        game.startGame();
        HelloApplication.setScene("board-view");

    }
}
