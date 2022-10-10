module io.github.haappi.battleGame {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires com.google.gson;

    opens io.github.haappi.battleGame to
      javafx.fxml;

  exports io.github.haappi.battleGame;
}
