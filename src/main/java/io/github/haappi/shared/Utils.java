package io.github.haappi.shared;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.concurrent.ConcurrentHashMap;

public class Utils {
    public static final ConcurrentHashMap<String, Image> imageHashMap = new ConcurrentHashMap<>();

    public static String charAt(String string, int position) {
        return String.valueOf(string.charAt(position));
    }

        public static String getContentOfMessage(String message) {
        return message.split(":")[1];
    }

    public static ImageView getImageViewFromGridPane(GridPane gridPane, int row, int col){
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (ImageView) node;
            }
        }
        return null;
    }
}
