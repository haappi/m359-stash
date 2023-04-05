package io.github.haappi.bold_client;

import io.github.haappi.packets.Card;

import io.github.haappi.packets.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static io.github.haappi.shared.Utils.getContentOfMessage;

public class GameView {
    public GridPane gameBoard = new GridPane();
    public Card[][] cards = new Card[5][4];
    public Label whosTurn = new Label();
    private final ArrayList<Card> selectedCards = new ArrayList<>();

    public static GameView instance;
    public Label scoreLabel;

    private boolean playerTurn = false;

    public static void messageReceived(final String object) {
        if (object.startsWith("score:")) {
            String content = getContentOfMessage(object);
            GameView.getInstance().scoreLabel.setText("Score: " + content);
            return;
        }
        if (object.startsWith("flipAllCards")) {
           for (Card[] cardRow : GameView.getInstance().cards) {
               for (Card card : cardRow) {
                   card.flip();
               }
           }
        }
    }

    public static void objectReceived(Object object) {
        if (object instanceof Card[][]) {
           Platform.runLater(() ->  GameView.getInstance().updateCards((Card[][]) object));
        }
    }

    @FXML
    protected void initialize() {
        GameView.instance = this;
        gameBoard = new GridPane();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                gameBoard.add(new Label("X"), i, j);
            }
        }
        bigInitFunction();
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
                Card card = HelloApplication.allCards.get(cards[i][j].getCardName());
                gameBoard.add(card, j, i);
                int finalI = i;
                int finalJ = j;
                card.setOnMouseClicked(event -> {
                    if (playerTurn) {
                        Client.getInstance().sendObject("cardClicked:" + finalI + "," + finalJ);
                    }
                });
                GridPane.setConstraints(cards[i][j], j, i);
                System.out.println(gameBoard.getChildren());
            }
        }
    }

    private void bigInitFunction() {
        gameBoard.setHgap(10.0);
        gameBoard.setVgap(10.0);
        gameBoard.setPadding(new Insets(10, 10, 10, 10));
    }
}
