module io.github.haappi.productivityApp {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
    requires firebase.admin;

    opens io.github.haappi.productivityApp to
      javafx.fxml;

  exports io.github.haappi.productivityApp;
    exports io.github.haappi.productivityApp.Timers;
    opens io.github.haappi.productivityApp.Timers to javafx.fxml;
}
