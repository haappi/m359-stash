package io.github.haappi.wordSearchTemplate;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class ClickedLetter {
    private final Text text;
    private final int row;
    private final int column;
    private final Paint oldColor;

    public ClickedLetter(Text text) {
        this.text = text;
        this.row = GridPane.getRowIndex(text);
        this.column = GridPane.getColumnIndex(text);
        this.oldColor = text.getFill();

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

    public Paint getOldColor() {
        return this.oldColor;
    }

}
