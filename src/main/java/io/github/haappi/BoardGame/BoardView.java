package io.github.haappi.BoardGame;

import io.github.haappi.BoardGame.Actions.Action;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static io.github.haappi.BoardGame.HelloApplication.game;

public class BoardView {
    public static final Rectangle[][] rectangles = new Rectangle[25][25];
    public static Label personTurnStatic, moreInformationStatic, actionsLabelStatic;
    public static Text eventInformationStatic;
    public static ListView<Action> actionsViewStatic;
    public static ListView<Card> cardViewStatic;
    public static ImageView mapStatic;
    public GridPane gridPane;
    @FXML
    protected Label actionsLabel;
    @FXML
    protected Label personTurn;
    @FXML
    protected ListView<Action> actionsView;
    @FXML
    protected ListView<Card> cardView;
    @FXML
    protected ImageView map;
    @FXML
    protected Label moreInformation;
    @FXML
    protected ListView listView;
    @FXML
    protected Text eventInformation;

    public static void updateExtraInformation(String data) {
        moreInformationStatic.setText(data);
    }

    public static void updateExtraInformation() {
        moreInformationStatic.setText(String.format("""
                                Current City: %s
                """, game.getCurrentPlayer().getCurrentCity()));
    }


    @FXML
    protected void initialize() {
        personTurnStatic = personTurn;
        actionsViewStatic = actionsView;
        actionsLabelStatic = actionsLabel;
        cardViewStatic = cardView;
        mapStatic = map;
        moreInformationStatic = moreInformation;
        eventInformationStatic = eventInformation;

        map.setImage(Utils.fileStreamToImage(Utils.getImage("labeled-map-of-north-america.png")));
        map.setFitHeight(500);
        game.nextPlayer();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Rectangle rect = new Rectangle(18, 18);
//                rect.setFill(Color.WHITE);
                rect.opacityProperty().set(0);
                int finalI = i;
                int finalJ = j;
                rect.setOnMouseClicked(event -> {
                    System.out.println(finalI + " " + finalJ);
                    rect.opacityProperty().set(100);
                    rect.setFill(Color.YELLOW);
                });
                rectangles[i][j] = rect;
                gridPane.add(rect, i, j);
            }
        }
        gridPane.setGridLinesVisible(true);
//        listView.getItems().addAll(HelloApplication.game.getEvents());
    }

    @FXML
    protected void doAction(ActionEvent event) {
        Action action = actionsView.getSelectionModel().getSelectedItem();
        action.doAction();
        game.updateStuff();

    }

    @FXML
    protected void endTurn(ActionEvent event) {
    }
}
