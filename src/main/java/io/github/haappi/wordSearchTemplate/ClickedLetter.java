package io.github.haappi.wordSearchTemplate;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ClickedLetter {
    private final Text text;
    private final int row;
    private final int column;

    public ClickedLetter(Text text) {
        this.text = text;
        this.row = GridPane.getRowIndex(text);
        this.column = GridPane.getColumnIndex(text);
    }

    public Text getText() {
        return text;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


}
