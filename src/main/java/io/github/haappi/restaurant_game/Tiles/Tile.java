package io.github.haappi.restaurant_game.Tiles;

import com.google.gson.annotations.Expose;

import io.github.haappi.restaurant_game.HelloApplication;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Tile extends Region {
    @Expose private final String style;
    @Expose private final int x;
    @Expose private final int y;
    @Expose private final int prefWidthHeight;

    public Tile(Color color, int x, int y, int prefWidthHeight) {
        //        super();
        String hex =
                String.format(
                        "#%02x%02x%02x",
                        (int) (color.getRed() * 255),
                        (int) (color.getGreen() * 255),
                        (int) (color.getBlue() * 255));
        this.style = "-fx-background-color: " + hex + ";";
        this.prefWidthHeight = prefWidthHeight;
        this.x = x;
        this.y = y;

        AnchorPane parent = HelloApplication.getInstance().getCurrentPane();
        contrusct();

        //        super.hoverProperty()
        //                .addListener(
        //                        (observable, oldValue, newValue) -> {
        //                            if (newValue) { // if hovered
        //                                MousePosition.TEXT.setText("X: " + x + " Y: " + y);
        //                            } else { // if not hovered
        //
        //                            }
        //                        });
    }

    public Tile contrusct() {
        super.setStyle(style);
        super.setPrefSize(this.prefWidthHeight, this.prefWidthHeight);
        return this;
    }

    public ObservableList<Node> getChildreen() {
        return getChildren();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTileColor() {
        return Color.color(
                        Integer.parseInt(style.substring(17, 19), 16),
                        Integer.parseInt(style.substring(21, 23), 16),
                        Integer.parseInt(style.substring(25, 27), 16))
                .toString();
    }
}
