package io.github.haappi.views;

import static io.github.haappi.Utils.sleepAndRunLater;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Theme;
import io.github.haappi.*;
import java.io.IOException;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginHandler {
  public Label label;
  public TextField email;
  public PasswordField password;
  public VBox vbox;

  @FXML private View primary;

  public static View load() {
    try {
      return FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
    } catch (IOException e) {
      System.out.println("IOException: " + e);
      return new View();
    }
  }

  public void initialize() {
    primary
        .showingProperty()
        .addListener(
            (obs, oldValue, newValue) -> {
              if (newValue) {
                AppBar appBar = AppManager.getInstance().getAppBar();
                appBar.setNavIcon(
                    MaterialDesignIcon.MENU.button(
                        e -> AppManager.getInstance().getDrawer().open()));
                appBar.setTitleText("Primary");
              }
            });
  }

  @FXML
  void login() throws IOException {
    GlobalHttpClass globalHttpClass = GlobalHttpClass.getHttpClient();
    Response resp = globalHttpClass.login(email.getText(), password.getText());
    label.setText(resp.getMessage());
    if (resp.succeeded()) {
      System.out.println("a");
      sleepAndRunLater(
          () -> {
            Platform.runLater(
                () -> {
                  System.out.println("bb");
                  //
                  // AppManager.getInstance().switchView(ViewEnums.HABITS.toString());
                });
          });
      DrawerManager.buildDrawer(AppManager.getInstance());
      sleepAndRunLater(
          () -> {
            try {
              HashMap<String, String> config =
                  Storage.getInstance().loadConfig(Config.getInstance().getEmail());

              if (config == null) {
                config = new HashMap<>();
                config.put("darkModeEnabled", "false");
                config.put("theme", "BLUE");
              }

              if (config.get("darkModeEnabled").equals("true")) {
                Theme.DARK.assignTo(primary.getScene());
              } else {
                Theme.LIGHT.assignTo(primary.getScene());
              }

              try {
                Swatching enumm = Swatching.valueOf(config.get("theme"));
                enumm.assignTo(primary.getScene());
              } catch (IllegalArgumentException e) {
              }
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          },
          false);
    }
  }

  public void signup(ActionEvent actionEvent) throws IOException {
    GlobalHttpClass globalHttpClass = GlobalHttpClass.getHttpClient();
    Response resp = globalHttpClass.signup(email.getText(), password.getText());
    label.setText(resp.getMessage());
    if (resp.succeeded()) {
      sleepAndRunLater(
          () -> {
            Platform.runLater(
                () -> {
                  AppManager.getInstance().switchView("Secondary View");
                });
          });
      DrawerManager.buildDrawer(AppManager.getInstance());
      sleepAndRunLater(
          () -> {
            try {
              Storage.getInstance().loadConfig(Config.getInstance().getEmail());
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          },
          false);
    }
  }

  public void resetPassword() throws IOException {
    GlobalHttpClass globalHttpClass = GlobalHttpClass.getHttpClient();
    Response resp = globalHttpClass.resetPassword(email.getText());
    label.setText(resp.getMessage());
  }
}
