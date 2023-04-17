module io.github.haappi.ProductivityApp {
    requires com.gluonhq.charm.glisten;
    requires javafx.fxml;
    requires com.gluonhq.attach.lifecycle;
    requires com.gluonhq.attach.util;

//    opens io.github.haappi to com.gluonhq.charm.glisten;
    opens io.github.haappi.views to javafx.fxml;
    exports io.github.haappi;
}