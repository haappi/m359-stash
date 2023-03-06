module io.github.haappi.bold_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires org.jetbrains.annotations;

    opens io.github.haappi.bold_client to
            javafx.fxml;

    exports io.github.haappi.bold_client;
    exports io.github.haappi.shared;

    opens io.github.haappi.shared to
            javafx.fxml;

    exports io.github.haappi.packets;

    opens io.github.haappi.packets to
            javafx.fxml;
}
