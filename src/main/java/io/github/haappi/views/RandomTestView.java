package io.github.haappi.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.HelloApplication;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class RandomTestView extends View {
  public View secondary;
  public VBox vbox;

  public View getView() {
    try {
      View view = FXMLLoader.load(HelloApplication.class.getResource("third.fxml"));
      return view;
    } catch (IOException e) {
      System.out.println("IOException: " + e);
      return new View();
    }
  }

  //    public RandomTestView() {
  //        super("Secondary");
  //    }

  @FXML
  public void initialize() {
    secondary.setShowTransitionFactory(BounceInRightTransition::new);

    FloatingActionButton fab =
        new FloatingActionButton(MaterialDesignIcon.INFO.text, e -> System.out.println("Info"));
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
                appBar.setTitleText("Secondary");
                appBar
                    .getActionItems()
                    .add(MaterialDesignIcon.FAVORITE.button(e -> System.out.println("Favorite")));
              }
            });
  }
}
