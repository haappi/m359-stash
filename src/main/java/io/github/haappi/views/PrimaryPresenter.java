package io.github.haappi.views;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.GlobalHttpClass;
import io.github.haappi.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static io.github.haappi.Utils.sleepAndRunLater;

public class PrimaryPresenter {

    public TextField email;
    public PasswordField password;
    @FXML
    private View primary;

    @FXML
    private Label label;

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
    void buttonClick() throws IOException {
        GlobalHttpClass globalHttpClass = GlobalHttpClass.getHttpClient();
        Response resp = globalHttpClass.login(email.getText(), password.getText());
        if (resp.succeeded()) {
            label.setText("Login succeeded");
            sleepAndRunLater(2000, () -> {
                Platform.runLater(() -> {
                    AppManager.getInstance().switchView("Secondary View");
                });
            });
        } else {
            label.setText("Login failed");
        }
    }
}
