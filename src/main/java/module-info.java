module io.github.haappi.library {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;

  opens io.github.haappi.library to
      javafx.fxml;

  exports io.github.haappi.library;
}
