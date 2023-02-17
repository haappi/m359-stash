package io.github.haappi.restaurant_game;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.bson.Document;

public class HelloController {
    public TextField gameCodeInput;

    @FXML
    protected void onStartButtonClick() {
        Document _game =
                DBHandler.getInstance()
                        .findDocument(
                                gameCodeInput.getText() != null ? gameCodeInput.getText() : "test");
        Game game =
                _game == null
                        ? new Game(gameCodeInput.getText())
                        : HelloApplication.gson.fromJson(_game.toJson(), Game.class);

        //        game.autoSave(500000); // 5 minutes
        game.autoSave(30000); // 30 seconds
        game.startDoingWeatherTask();
        game.startDoingPartyTask();

        HelloApplication.getInstance().setGameInstance(game);
        HelloApplication.getInstance().setStageScene("game-menu-view");
    }
}
