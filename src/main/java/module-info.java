module io.github.haappi.BoardGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.BoardGame to
            javafx.fxml;

    exports io.github.haappi.BoardGame;
    exports io.github.haappi.BoardGame.Actions;

    opens io.github.haappi.BoardGame.Actions to
            javafx.fxml;
}
