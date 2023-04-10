package io.github.haappi.bold_client;

import io.github.haappi.packets.Card;

import io.github.haappi.packets.Player;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

import static io.github.haappi.packets.Card.backCardURI;
import static io.github.haappi.shared.Utils.getContentOfMessage;
import static io.github.haappi.shared.Utils.getImageViewFromGridPane;
import static javafx.scene.transform.Rotate.Y_AXIS;

public class GameView {
    public GridPane gameBoard = new GridPane();
    public Label whosTurn = new Label();
    private static final ArrayList<Card> selectedCards = new ArrayList<>();

    public static GameView instance;
    public Label scoreLabel;

    public static Label staticScoreLabel;
    public static Label playerTurnLabel;
    public static GridPane staticGameBoard;

    private static boolean playerTurn = false;

    private static Card[][] cards = new Card[4][5];

    public static void messageReceived(final String object) {
        System.out.println(object);
        if (object.startsWith("startGame")) {
            HelloApplication.getInstance().loadFxmlFile("connect-menu");
            return;
        }
        if (object.startsWith("score:")) {
            String content = getContentOfMessage(object);
            GameView.getInstance().scoreLabel.setText("Score: " + content);
            return;
        }
        if (object.startsWith("yourTurn")) {
            playerTurn = true;
            playerTurnLabel.setText("It is YOUR turn.");
            return;
        }
        if (object.startsWith("notYourTurn")) {
            playerTurn = false;
            playerTurnLabel.setText("It is NOT YOUR turn.");
            return;
        }
        if (object.startsWith("flipAllCards")) {
            Image image = HelloApplication.allCardImages.get("back");
            for (Card[] cardRow : GameView.getInstance().cards) {
               for (Card card : cardRow) {
                   ImageView view = getImageViewFromGridPane(staticGameBoard, card.getRow(), card.getCol());
                   RotateTransition transition = new RotateTransition(Duration.millis(500), view);
                   transition.setAxis(Y_AXIS);
                   transition.setFromAngle(90);
                   transition.setToAngle(0);
                   transition.setOnFinished(e2 -> {
                    view.setImage(image);
                    transition.setFromAngle(90);
                    transition.setToAngle(0);
                    transition.play();
                    transition.setOnFinished(e3 -> {
                        transition.stop();
                    });
                });
                transition.play();
               }
           }
        }
    }

    public static void messageReceived(Object object) {
        if (object instanceof Card[][]) {
            Platform.runLater(() ->  GameView.getInstance().updateCards((Card[][]) object));
            return;
        }
        if (object instanceof Card card) {
            ImageView view = getImageViewFromGridPane(staticGameBoard, card.getRow(), card.getCol());
            Image image = HelloApplication.allCardImages.get(card.getCardName());

            RotateTransition transition = new RotateTransition(Duration.millis(500), view);
            transition.setAxis(Y_AXIS);
            transition.setFromAngle(0);
            transition.setToAngle(90);

            transition.setOnFinished(event -> {
                view.setImage(image);
                transition.setToAngle(180);
                transition.play();
                transition.setOnFinished(e3 -> {
                    transition.stop();
                    selectedCards.add(cards[card.getRow()][card.getCol()]);
                });
            });
            transition.play();
            view.setOnMouseClicked(null);
            return;
        }
        if (object instanceof Integer) {
            staticScoreLabel.setText("Your Score: " + object);
        }
    }

    @FXML
    protected void initialize() {
        GameView.instance = this;
        GameView.staticScoreLabel = scoreLabel;
        GameView.staticGameBoard = gameBoard;
        GameView.playerTurnLabel = whosTurn;
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

    public void updateCards(final Card[][] cards) {
        gameBoard.getChildren().clear();
        this.cards = cards;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                ImageView imageView = new ImageView();
                int finalI = i;
                int finalJ = j;
                imageView.setOnMouseClicked(event -> {
                    if (playerTurn) {
                        Client.getInstance().sendObject("cardClicked:" + finalI + "," + finalJ);
                    }
                });
                imageView.setImage(HelloApplication.allCardImages.get("back"));
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
