module io.github.haappi.psuedoCode {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;

  opens io.github.haappi.psuedoCode to
      javafx.fxml;

  exports io.github.haappi.psuedoCode;
}
