package io.github.haappi.views;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Theme;
import io.github.haappi.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Settings {
    public View primary;
    public VBox vbox;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
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


        SwitchButton switchButton = new SwitchButton("Light Mode", "Dark Mode", Boolean.parseBoolean(Config.getInstance().getConfig().get("darkModeEnabled")));
        switchButton.addListener((old, oldd, newe) -> {
            if (old.getValue().booleanValue()) {
                Theme.LIGHT.assignTo(primary.getScene());
            } else {
                Theme.DARK.assignTo(primary.getScene());
            }

            try {
                Storage.getInstance().setConfigValue(Config.getInstance().getEmail(), "darkModeEnabled", old.getValue().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        vbox.getChildren().add(switchButton);


        ObservableList<Swatching> listInstance = FXCollections.observableArrayList(Swatching.getAllAsList());
        final ComboBox<Swatching> comboBox = new ComboBox<>(listInstance);
        comboBox.setOnAction((event) -> {
            Swatching swatching = comboBox.getSelectionModel().getSelectedItem();
            if (swatching != null) {
                swatching.assignTo(primary.getScene());
            }

            try {
                Storage.getInstance().setConfigValue(Config.getInstance().getEmail(), "theme", swatching != null ? swatching.name() : "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        vbox.getChildren().add(comboBox);
    }
}
