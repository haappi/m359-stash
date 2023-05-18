package io.github.haappi.views;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.GlobalHttpClass;
import io.github.haappi.HelloApplication;
import io.github.haappi.Response;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static io.github.haappi.Utils.sleepAndRunLater;

public class LoginHandler {
    public Label label;
    public TextField email;
    public PasswordField password;

    @FXML
    private View primary;


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
                                appBar
                                        .getActionItems()
                                        .add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
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
            sleepAndRunLater(() -> {
                Platform.runLater(() -> {
                    System.out.println("bb");
                    AppManager.getInstance().switchView(ViewEnums.HMMM.toString());
                });
            });
        }
    }

    public void signup(ActionEvent actionEvent) throws IOException {
                GlobalHttpClass globalHttpClass = GlobalHttpClass.getHttpClient();
        Response resp = globalHttpClass.signup(email.getText(), password.getText());
        label.setText(resp.getMessage());
        if (resp.succeeded()) {
            sleepAndRunLater(() -> {
                Platform.runLater(() -> {
                    AppManager.getInstance().switchView("Secondary View");
                });
            });
        }

    }
}
