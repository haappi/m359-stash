module io.github.haappi.TicTacToe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.TicTacToe to
            javafx.fxml;

    exports io.github.haappi.TicTacToe;
}
