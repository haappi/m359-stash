module io.github.haappi.arraysProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.arraysProject to
            javafx.fxml;

    exports io.github.haappi.arraysProject;
}
