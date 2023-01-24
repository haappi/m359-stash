module io.github.haappi.template {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens io.github.haappi.template to
            javafx.fxml;

    exports io.github.haappi.template;
    exports io.github.haappi.template.DayOne;

    opens io.github.haappi.template.DayOne to
            javafx.fxml;

    exports io.github.haappi.template.Day2;

    opens io.github.haappi.template.Day2 to
            javafx.fxml;

    exports io.github.haappi.template.DayThree;

    opens io.github.haappi.template.DayThree to
            javafx.fxml;

    exports io.github.haappi.template.Day6;

    opens io.github.haappi.template.Day6 to
            javafx.fxml;
}
