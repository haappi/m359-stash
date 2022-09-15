module io.github.haappi.psuedoCode {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jetbrains.annotations;

    opens io.github.haappi.psuedoCode to
            javafx.fxml;

    exports io.github.haappi.psuedoCode;
}
