module io.github.haappi.BoardGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.BoardGame to
            javafx.fxml;

    exports io.github.haappi.BoardGame;
}
