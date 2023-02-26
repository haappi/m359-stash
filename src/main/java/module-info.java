module io.github.haappi.bold_server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens io.github.haappi.bold_server to
            javafx.fxml;

    exports io.github.haappi.bold_server;
    exports io.github.haappi.packets;

    opens io.github.haappi.packets to
            javafx.fxml;
}
