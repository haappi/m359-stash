module io.github.haappi.template {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.template to
            javafx.fxml;

    exports io.github.haappi.template;
}
