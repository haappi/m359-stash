module io.github.haappi.sample {
//  requires javafx.controls;
//  requires javafx.fxml;
      requires com.gluonhq.attach.lifecycle;
  requires java.desktop;
  requires com.gluonhq.charm.glisten;
  requires com.gluonhq.cloudlink.client;
  requires com.gluonhq.attach.util;
  requires com.gluonhq.attach.display;
//    requires firebase.admin;
//    requires com.google.auth.oauth2;

        // Requires directive for Firebase dependencies
//    requires com.google.firebase.auth;
//    requires com.google.firebase.database;
//    requires com.google.firebase.firestore;
//
//    // Requires directive for Google Cloud dependencies
//    requires com.google.cloud.storage;
//    requires com.google.cloud.core;
//    requires com.google.cloud.pubsub;

    opens io.github.haappi.sample to
      javafx.fxml;

  exports io.github.haappi.sample;
//    exports io.github.haappi.productivityApp.Timers;
//    opens io.github.haappi.sample to javafx.fxml;
}