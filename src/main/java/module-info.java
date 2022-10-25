module io.github.haappi.battleGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires org.jetbrains.annotations;

    opens io.github.haappi.battleGame to
            javafx.fxml;

    exports io.github.haappi.battleGame;
    exports io.github.haappi.battleGame.Classes;

}
