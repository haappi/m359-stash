package io.github.haappi;

import com.gluonhq.attach.display.DisplayService;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import io.github.haappi.views.PrimaryView;
import io.github.haappi.views.SecondaryView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.gluonhq.charm.glisten.application.AppManager.HOME_VIEW;

public class HelloApplication extends Application {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";

    private final AppManager appManager = AppManager.initialize(this::postInit);
    private Stage stage;

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
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
        this.stage = primaryStage;
         Services.get(DisplayService.class).ifPresent(service -> {
   Dimension2D resolution = service.getScreenResolution();
      System.out.printf("Screen resolution: %.0fx%.0f", resolution.getWidth(), resolution.getHeight());
  });
//        loadFxmlFile("views/primary.fxml");
//        Platform.runLater(() -> appManager.start(primaryStage));
        appManager.start(primaryStage);
    }

    private void loadFxmlFile(String filename) {
        filename = filename.replace(".fxml", "") + ".fxml";
        String finalFilename = filename;
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader =
                            new FXMLLoader(HelloApplication.class.getResource(finalFilename));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 1600, 900);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                });
    }

    private void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(ProductivityApp.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(ProductivityApp.class.getResourceAsStream("/icon.png")));
    }
}
