module io.github.haappi {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires com.gluonhq.attach.lifecycle;
    requires com.gluonhq.attach.util;
    requires com.gluonhq.attach.storage;
    requires com.gluonhq.attach.statusbar;
    requires com.gluonhq.attach.display;

    opens io.github.haappi to javafx.fxml;
    opens io.github.haappi.views to javafx.fxml;
    exports io.github.haappi;
    exports io.github.haappi.views;
}