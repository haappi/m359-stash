package io.github.haappi.restaurant_game.Tiles;

import io.github.haappi.restaurant_game.HelloApplication;
import io.github.haappi.restaurant_game.MousePosition;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Tile extends Button {
    private final Color tileColor;
    private final int x;
    private final int y;
    private Text thingy;

    public Tile(Color color, int x, int y, int prefWidthHeight) {
        super();
        this.tileColor = color;
        super.setStyle("-fx-background-color: " + color.toString().substring(2));
        super.setPrefSize(prefWidthHeight, prefWidthHeight);
        this.x = x;
        this.y = y;

        AnchorPane parent = HelloApplication.getInstance().getCurrentPane();

        super.hoverProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue) { // if hovered
                                MousePosition.TEXT.setText("X: " + x + " Y: " + y);
                            } else { // if not hovered

                            }
                        });
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getTileColor() {
        return tileColor;
    }
}
