package io.github.haappi.bold_client;

import io.github.haappi.packets.Card;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

import static io.github.haappi.shared.Utils.getContentOfMessage;
import static io.github.haappi.shared.Utils.getImageViewFromGridPane;
import static javafx.scene.transform.Rotate.Y_AXIS;

public class GameView {
    public GridPane gameBoard;
    public Label whosTurn = new Label();
    private static final ArrayList<Card> selectedCards = new ArrayList<>();

    public static GameView instance;
    public Label scoreLabel;

    public static Label staticScoreLabel;
    public static Label playerTurnLabel;
    public static GridPane staticGameBoard;

    private static boolean playerTurn = false;

    public static Card[][] cards = new Card[4][5];
    public AnchorPane pane;

    public static void messageReceived(final String object) {
        System.out.println("String received: " + object);
        if (object.startsWith("start")) {
            HelloApplication.getInstance().loadFxmlFile("game-view");
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
            ArrayList<RotateTransition> transitions = new ArrayList<>();
            Image image = HelloApplication.allCardImages.get("back");
            for (Card[] cardRow : GameView.cards) {
               for (Card card : cardRow) {
                   ImageView view = getImageViewFromGridPane(staticGameBoard, card.getRow(), card.getCol());
                   if (view.getImage() == image) {
                       continue;
                   }
                   RotateTransition transition = new RotateTransition(Duration.millis(500), view);
                   transition.setAxis(Y_AXIS);
                   transition.setFromAngle(180);
                   transition.setToAngle(90);
                   transition.setAutoReverse(true); // goes to the left once, goes to the right once. etc.
                   transition.setOnFinished(event -> {
                    view.setImage(image);
                    transition.setFromAngle(90);
                    transition.setToAngle(0);
                    transition.play();
                    transition.setOnFinished(eventt -> {
                        transition.stop();
                    });
                });
                   transitions.add(transition);
               }
            }
            transitions.forEach(RotateTransition::play);
            return;
        }
        if (object.startsWith("gameOver")) {
            String message = getContentOfMessage(object);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Concluded!");
            alert.setHeaderText("The game has concluded. Please look at the server to restart the game.");
            alert.setContentText(message);
        }
    }

    public static void messageReceived(Object object) {
        System.out.println("Object received: " + object);
        if (object instanceof Card[][]) {
            Platform.runLater(() ->  GameView.getInstance().updateCards((Card[][]) object));
            return;
        }
        if (object instanceof Card card) {
            ImageView view = getImageViewFromGridPane(staticGameBoard, card.getRow(), card.getCol());
            System.out.println(HelloApplication.allCardImages.size());

            // sout all entires in HashMap allCardImages

            for (String key : HelloApplication.allCardImages.keySet()) {
                System.out.println(key);
            }
            System.out.println("looking for: " + card.getCardName().replace(".png", "") + " in HashMap");

            Image image = HelloApplication.allCardImages.get(card.getCardName().replace(".png", ""));
            System.out.println(image.getUrl());

            RotateTransition transition = new RotateTransition(Duration.millis(500), view);
            transition.setAxis(Y_AXIS);
            transition.setFromAngle(0);
            transition.setToAngle(90);
            transition.setAutoReverse(true); // goes to the left once, goes to the right once. etc.

            transition.setOnFinished(event -> {
                view.setImage(image);
                transition.setToAngle(180);
                transition.setFromAngle(90);
                transition.play();
                transition.setOnFinished(e3 -> {
                    transition.stop();
                    selectedCards.add(cards[card.getRow()][card.getCol()]);
                });
            });
            transition.play();
//            view.setOnMouseClicked(null);
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
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 4; j++) {
//                gameBoard.add(new Label("X"), i, j);
//            }
//        }
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
        gameBoard.setVisible(true);
        GameView.cards = cards;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                ImageView imageView = new ImageView();
                int finalX = x;
                int finalY = y;
                imageView.setOnMouseClicked(event -> {
                    if (playerTurn) {
                        Client.getInstance().sendMessage("cardClicked:" + finalX + "," + finalY);
                    }
                });
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setImage(HelloApplication.allCardImages.get("back"));
                System.out.println("Adding image to gridpane at " + x + ", " + y);
                gameBoard.add(imageView, y, x);
            }
        }
        System.out.println(gameBoard.getChildren());
    }

    private void bigInitFunction() {
        gameBoard.setHgap(10.0);
        gameBoard.setVgap(10.0);
        gameBoard.setPadding(new Insets(10, 10, 10, 10));
    }
}
