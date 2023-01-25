module io.github.haappi.restaurant_game {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires com.google.gson;
  requires org.mongodb.driver.core;
requires org.mongodb.driver.sync.client;

  opens io.github.haappi.restaurant_game to
          javafx.fxml;

  exports io.github.haappi.restaurant_game;
}
