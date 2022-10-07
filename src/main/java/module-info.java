module io.github.haappi.battleGame {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;

  opens io.github.haappi.battleGame to
      javafx.fxml;

  exports io.github.haappi.battleGame;
}
