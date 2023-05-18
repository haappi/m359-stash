package io.github.haappi.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class YetAnotherShittyView {

        @FXML
    private View secondary;
    public static View getView() {
        try {
            View view = FXMLLoader.load(HelloApplication.class.getResource("bah.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }

        public void initialize() {
        secondary.setShowTransitionFactory(BounceInRightTransition::new);

        FloatingActionButton fab =
                new FloatingActionButton(MaterialDesignIcon.INFO.text, e -> System.out.println("Infooooooooo"));
        fab.showOn(secondary);

        secondary
                .showingProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {
                            if (newValue) {
                                AppBar appBar = AppManager.getInstance().getAppBar();
                                appBar.setNavIcon(
                                        MaterialDesignIcon.MENU.button(
                                                e -> AppManager.getInstance().getDrawer().open()));
                                appBar.setTitleText("im fat");
                                appBar
                                        .getActionItems()
                                        .add(MaterialDesignIcon.FAVORITE.button(e -> System.out.println("Favorite")));
                            }
                        });

    }

}
