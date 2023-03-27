package io.github.haappi.bold_client;

import io.github.haappi.packets.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameView {
    public GridPane gameBoard = new GridPane();
    public Card[][] cards = new Card[5][4];
    public Label whosTurn = new Label();

    public static GameView instance;

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

    public GameView() {
        instance = this;
    }

    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }

    public void updateCards(Card[][] cards) {
        gameBoard.getChildren().clear();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                gameBoard.add(HelloApplication.allCards.get(cards[i][j].getCardName()), j, i);
                GridPane.setConstraints(cards[i][j], j, i);
                //                System.out.println(cards[i][j].getFileURI());
                //                cards[i][j].setImage(Card.getImage(cards[i][j].getFileURI()));
                System.out.println(gameBoard.getChildren());
            }
        }
    }
}
