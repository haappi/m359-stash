module io.github.haappi.BoardGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires redis.clients.jedis;
    requires com.google.gson;

    opens io.github.haappi.BoardGame to
            javafx.fxml,
            com.google.gson;

    exports io.github.haappi.BoardGame;
}
