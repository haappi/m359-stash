module io.github.haappi.ProductivityApp {
    requires com.gluonhq.charm.glisten;
    requires javafx.fxml;
    requires com.gluonhq.attach.lifecycle;
    requires com.gluonhq.attach.util;
    requires com.gluonhq.attach.display;
    requires com.gluonhq.attach.storage;

    opens io.github.haappi.views to javafx.fxml;
    exports io.github.haappi;
}