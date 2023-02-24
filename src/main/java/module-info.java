module io.github.haappi.bold_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens io.github.haappi.bold_client to
            javafx.fxml;

    exports io.github.haappi.bold_client;
}
