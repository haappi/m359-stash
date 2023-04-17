module io.github.haappi.productivityApp {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
    requires firebase.admin;
    requires com.google.auth.oauth2;

        // Requires directive for Firebase dependencies
//    requires com.google.firebase.auth;
//    requires com.google.firebase.database;
//    requires com.google.firebase.firestore;
//
//    // Requires directive for Google Cloud dependencies
//    requires com.google.cloud.storage;
//    requires com.google.cloud.core;
//    requires com.google.cloud.pubsub;

    opens io.github.haappi.productivityApp to
      javafx.fxml;

  exports io.github.haappi.productivityApp;
    exports io.github.haappi.productivityApp.Timers;
    opens io.github.haappi.productivityApp.Timers to javafx.fxml;
}
