package io.github.haappi.restaurant_game;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.bson.Document;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final Gson gson = new Gson();

    public static void main(String[] args) {
        launch();
    }

    private Stage stage;
    private int width = 1600;
    private int height = 900; // this is one below 1920x1080
    public static HelloApplication instance;

    public static HelloApplication getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        HelloApplication.instance = this;
        /*
        Possible useful things
        https://github.com/karakun/OpenWebStart/blob/master/documentation/faq/FAQ.adoc#how-to-run-openjfx-based-javafx-applications-with-openwebstart
        https://docs.webfx.dev/#_getting_started

        https://stackoverflow.com/questions/27958019/embed-a-javafx-application-in-a-html-webpage
        https://docs.oracle.com/javase/9/deploy/self-contained-application-packaging.htm#JSDPG583
         */
        setStageScene("hello-view");
        testClass ok = new testClass("fhsduifhsd");
        //        Document docc = DBHandler.getInstance().insert(new ArrayList<>(List.of(ok, ok, 1,
        // "a")), DBHandler.getInstance().getCollection("test", "monkey"));
        Document doc =
                DBHandler.getInstance()
                        .insert(ok, DBHandler.getInstance().getCollection("test", "monkey"));
        System.out.println(DBHandler.getInstance().getClassFromDocument(doc, testClass.class));
        //        System.out.println((HelloApplication.gson.fromJson(doc.toJson(),
        // testClass.class)));
    }

    /**
     * Handles changing the Scene of the Stage for you.
     *
     * @param fileName The name of the fxml file to load.
     */
    public void setStageScene(String fileName) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(
                            HelloApplication.class.getResource(
                                    fileName.replace(".fxml", "") + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            HelloApplication.getInstance().setStageScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStageScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
}
