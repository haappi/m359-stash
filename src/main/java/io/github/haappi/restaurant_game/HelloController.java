package io.github.haappi.restaurant_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    public TextField gameCodeInput;
    @FXML private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        //        System.out.println("a");
        //        throw new IllegalStateException("This is a test");
        //        Document game = DBHandler.getInstance().findDocument(gameCodeInput.getText());
        //        Game gameInstance;
        //        if (game == null) {
        //            gameInstance = new Game(gameCodeInput.getText());
        //            gameInstance.setTest(5);
        //        } else {
        //            gameInstance = HelloApplication.gson.fromJson(game.toJson(), Game.class);
        //            gameInstance.setTest(120);
        //        }
        //        System.out.println(HelloApplication.gson.toJson(gameInstance));
        //        System.out.println(
        //                DBHandler.getInstance()
        //                        .insert(
        //                                gameInstance,
        //                                DBHandler.getInstance()
        //                                        .getCollection(DBHandler.dbName,
        // DBHandler.collectionName))
        //                        .get("_id"));
        //        // https://www.callicoder.com/java-8-completablefuture-tutorial/
        //        // todo - prolly dont need to do that, since i'll only load the game once,
        //        //         (auto)save every
        //        // 5 minutes or so, and save when program closed // when they exit their session.
        //        System.out.println(gameInstance.getTest());
        //        welcomeText.setText("Welcome to JavaFX Application!");
        HelloApplication.getInstance().setStageScene("restaurant-view");
    }

    public void gamer(ActionEvent actionEvent) {
        Game game = new Game("test");
        HelloApplication.getInstance().setGameInstance(game);
        HelloApplication.getInstance().setStageScene("restaurant-view");
    }
}
