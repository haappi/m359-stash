package io.github.haappi.bold_server;

import io.github.haappi.bold_server.Packets.CloseServer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloApplication extends Application {
    private static HelloApplication instance;
    private final ArrayList<Server> servers = new ArrayList<>();
    private Stage stage;

    public static synchronized HelloApplication getInstance() {
        return instance;
    }

    public static void main(String[] args) throws IOException {
        launch();
    }

    public synchronized void addServer(Server server) {
        servers.add(server);
    }

    public synchronized void removeServer(Server server) {
        servers.remove(server);
    }

    public synchronized void removeServer(String name) {
        servers.removeIf(server -> server.getName().equals(name));
    }

    public synchronized ArrayList<Server> getServers() {
        return servers;
    }

    @Override
    public void start(Stage stage) {
        instance = this;
        this.stage = stage;
        new Thread(
                        () -> {
                            try {
                                while (true) {
                                    Scanner inputReader = new Scanner(System.in);
                                    String input = inputReader.nextLine();
                                    if (input.equals("exit")) {
                                        for (Server server : servers) {
                                            server.broadcast(new CloseServer());
                                            server.close();
                                        }
                                        System.exit(0);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        })
                .start();
        loadFxmlFile("main-view.fxml");
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
}
