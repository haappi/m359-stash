package io.github.haappi.BoardGame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;

public class BoardGameController {
    public GridPane innerGrid;
    public ImageView imageView;
    public Game game;

    @FXML
    protected void initialize() {
        game = new Game(Lobby.getConnectedUsers());
        game.getPlayers().forEach(player -> System.out.println(player.getName()));
        imageView.setImage(Utils.fileStreamToImage(Utils.getImage("labeled-map-of-north-america.png")));
        imageView.setFitHeight(1080);
        imageView.setFitWidth(1920);
        imageView.setPreserveRatio(true);


        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                innerGrid.add(new Rectangle(10, 10), j, i);
//                innerGrid.add(new Label(j + "," + i), j, i);
            }
        }
        int thing = 50;
//        innerGrid.getColumnConstraints().addAll(new ColumnConstraints(thing), new ColumnConstraints(thing), new ColumnConstraints(thing));
//        innerGrid.getRowConstraints().addAll(new RowConstraints(thing), new RowConstraints(thing), new RowConstraints(thing));
        innerGrid.setGridLinesVisible(true);
//        innerGrid.setPrefSize(thing, thing);
    }
}
