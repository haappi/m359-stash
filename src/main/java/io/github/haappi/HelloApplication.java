package io.github.haappi;

import com.gluonhq.attach.display.DisplayService;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.visual.Swatch;
import io.github.haappi.views.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    public static Properties properties;
    private static HelloApplication instance;
    private final AppManager appManager = AppManager.initialize(this::postInit);
    private boolean isDarkMode = false;
    private Stage stage;

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler.getInstance());
        launch(args);
    }

    public HelloApplication getInstance() {
        if (instance == null) {
            instance = new HelloApplication();
        }
        return instance;
    }

    @Override
    public void init() throws IOException {
        HelloApplication.instance = this;
        Platform.runLater(
                () -> Thread.currentThread().setUncaughtExceptionHandler(ExceptionHandler.getInstance()));

        properties = new Properties();
        properties.setProperty("apiKey", "AIzaSyC8ULrlwfwUzha71jECnSds9b7IhbKZnPc");


//        appManager.addViewFactory(SECONDARY_VIEW, () -> new PrimaryView().getView());

        appManager.addViewFactory(ViewEnums.SPLASH.toString(), () -> LoginHandler.load());
        appManager.addViewFactory(ViewEnums.SETTINGS.toString(), () -> Settings.load());
        appManager.addViewFactory(ViewEnums.POMODORO.toString(), () -> Pomodoro.load());
        DrawerManager.buildDrawer(appManager);
    }

    @Override
    public void start(Stage primaryStage) {
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D screenBounds = primaryScreen.getVisualBounds();

        // Set application size to match screen dimensions
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());


        this.stage = primaryStage;
        Services.get(DisplayService.class)
                .ifPresent(
                        service -> {
                            Dimension2D resolution = service.getScreenResolution();
                            System.out.printf(
                                    "Screen resolution: %.0fx%.0f", resolution.getWidth(), resolution.getHeight());
                        });
        //        loadFxmlFile("views/primary.fxml");
        //        Platform.runLater(() -> appManager.start(primaryStage));
        //        FirebaseService firebaseService = new FirebaseService();
        appManager.start(primaryStage);
    }


    private void postInit(Scene scene) {
        Swatch.GREY.assignTo(scene);
//        toggleTheme();
        scene.getStylesheets().add(ProductivityApp.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow())
                .getIcons()
                .add(new Image(ProductivityApp.class.getResourceAsStream("/icon.png")));

//
//         FloatingActionButton fab = new FloatingActionButton();
//    fab.getStyleClass().add("floating-action-button");
//    StackPane mainContainer = new StackPane();
//    mainContainer.getChildren().add(getViewFactory().getInstance("Primary").getRoot());
//    Layer layer = new Layer();
//    layer.getChildren().addAll(mainContainer, fab);
//    scene.setRoot(layer);
//    scene.getStylesheets().addAll("style.css");


    }


    public void toggleTheme() {
        // Invert the boolean value
        isDarkMode = !isDarkMode;

        // Load the appropriate theme based on the current mode
        if (isDarkMode) {
            loadTheme("dark_mode.css");
        } else {
            loadTheme("light_mode.css");
        }
    }

    private void loadTheme(String fileName) {
        // Load the theme from the CSS file
        String theme = HelloApplication.class.getResource("views/" + fileName).toExternalForm();
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(theme);
    }

    @Override
    public void stop() {
        System.out.println("applcation is endeing");
    }
}

