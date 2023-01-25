module io.github.haappi.restaurant_game {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;

  opens io.github.haappi.restaurant_game to
      javafx.fxml;

  exports io.github.haappi.restaurant_game;
}
