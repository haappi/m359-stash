module io.github.haappi.wordSearchTemplate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.wordSearchTemplate to
            javafx.fxml;

    exports io.github.haappi.wordSearchTemplate;
}
