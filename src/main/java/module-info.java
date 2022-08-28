module io.github.haappi.storeProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.storeProject to
            javafx.fxml;

    exports io.github.haappi.storeProject;
}
