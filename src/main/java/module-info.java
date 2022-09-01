module io.github.haappi.fivechallenges {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.stock to
            javafx.fxml;

    exports io.github.haappi.stock;
}
