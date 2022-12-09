package io.github.haappi.BoardGame;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class BoardGameController {
    public Text ping;
    public AnchorPane innerPane;
    public GridPane innerGrid;

    @FXML
    protected void initialize() {
        //            Image image = new Image(Utils.getImage("map-of-north-america.png"));
        innerGrid.setStyle(
                "-fx-background-image: url('assets/labeled-map-of-north-america.png');"
                    + " -fx-background-repeat: no-repeat; width: 600px; height: 600px");
        //            innerGrid.add(new ImageView(image), 0, 0);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                innerGrid.add(new GridPane(), j, i);
            }
        }
        innerGrid.setGridLinesVisible(true);
        innerGrid.setPrefSize(1000, 1000);
    }
}
