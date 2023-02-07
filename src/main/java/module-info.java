module io.github.haappi.restaurant_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.jetbrains.annotations;
    requires org.slf4j;
    requires javafx.graphics;

    opens io.github.haappi.restaurant_game to
            javafx.fxml,
            com.google.gson;

    exports io.github.haappi.restaurant_game;
}
