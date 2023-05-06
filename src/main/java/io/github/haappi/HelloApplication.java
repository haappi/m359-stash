package io.github.haappi;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import io.github.haappi.views.PrimaryView;
import io.github.haappi.views.SecondaryView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

public class HelloApplication extends Application {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";

    private final AppManager appManager = AppManager.initialize(this::postInit);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        appManager.addViewFactory(PRIMARY_VIEW, () -> new PrimaryView().getView());
        appManager.addViewFactory(SECONDARY_VIEW, () -> new SecondaryView().getView());

        DrawerManager.buildDrawer(appManager);
    }

    @Override
    public void start(Stage primaryStage) {
        Platform.runLater(() -> appManager.start(primaryStage));
//        appManager.start(primaryStage);
    }

    private void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(ProductivityApp.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(ProductivityApp.class.getResourceAsStream("/icon.png")));
    }
}
