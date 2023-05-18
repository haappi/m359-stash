module io.github.haappi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires com.gluonhq.attach.lifecycle;
    requires com.gluonhq.attach.util;
    requires com.gluonhq.attach.storage;
    requires com.gluonhq.attach.statusbar;
    requires com.gluonhq.attach.display;
    // firebase
//  requires firebase.admin;
    requires org.slf4j;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires jdk.httpserver;
    requires com.gluonhq.attach.device;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.json;

    opens io.github.haappi to
            javafx.fxml;
    opens io.github.haappi.views to
            javafx.fxml;

    exports io.github.haappi;
    exports io.github.haappi.views;
}
