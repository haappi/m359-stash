package io.github.haappi.bold_server;

import io.github.haappi.packets.CloseServer;
import io.github.haappi.packets.ServerMessage;
import io.github.haappi.packets.StartGame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainView {
    private final ArrayList<Timer> timers = new ArrayList<>();
    @FXML protected ListView<Server> serverListView;
    @FXML protected TextField serverName;
    @FXML protected TextField bindIP;
    @FXML protected TextField bindPort;
    @FXML protected ListView<ClientHandler> connectedClients;
    @FXML protected TextField sendMessageClient;
    private Server selectedServer;
    private ClientHandler selectedClient;

    @FXML
    protected void initialize() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        ArrayList<Server> servers = HelloApplication.getInstance().getServers();
                        Platform.runLater(
                                () -> {
                                    serverListView.getItems().clear();
                                    serverListView.getItems().addAll(servers);

                                    if (selectedServer != null) {
                                        serverListView.getSelectionModel().select(selectedServer);
                                    }
                                });
                    }
                },
                0,
                1000);
        timers.add(timer);

        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (selectedServer == null) return;

                        ArrayList<ClientHandler> clients = selectedServer.getClients();
                        Platform.runLater(
                                () -> {
                                    connectedClients.getItems().clear();
                                    connectedClients.getItems().addAll(clients);

                                    if (selectedClient != null) {
                                        connectedClients.getSelectionModel().select(selectedClient);
                                    }
                                });
                    }
                },
                0,
                1000);
        timers.add(timer1);

        serverListView.setOnMouseClicked(
                mouseEvent ->
                        selectedServer = serverListView.getSelectionModel().getSelectedItem());
        connectedClients.setOnMouseClicked(
                mouseEvent ->
                        selectedClient = connectedClients.getSelectionModel().getSelectedItem());
    }

    @FXML
    protected void closeServer() throws IOException {
        if (selectedServer == null) return;
        selectedServer.close();
        HelloApplication.getInstance().removeServer(selectedServer);
    }

    @FXML
    protected void blockInc() {
        if (selectedServer == null) return;
        selectedServer.toggleAcceptingConnections();
    }

    @FXML
    protected void createServer() {
        String name =
                serverName.getText().equals("")
                        ? "Server - " + System.currentTimeMillis()
                        : serverName.getText();
        String ip = bindIP.getText().equals("") ? "localhost" : bindIP.getText();
        int port = bindPort.getText().equals("") ? 2005 : Integer.parseInt(bindPort.getText());

        if (port < 0 || port > 65535) {
            System.out.println("Invalid port");
            return;
        }

        for (Server server : HelloApplication.getInstance().getServers()) {
            if (server.getPortListening() == port && server.getIpListening().equals(ip)) {
                System.out.println("Server already exists");
                return;
            }
        }

        try {
            Server server = new Server(name, port, ip);
            HelloApplication.getInstance().addServer(server);
            new Thread(server::start).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void startGameButton() throws IOException {
        if (this.selectedServer == null) return;
        if (this.selectedServer.getGameInstance().getPlayers().size() == 0) {
            return;
        }
        // check if any of the players are NOT ready
        //        for (Player player : this.selectedServer.getGameInstance().getPlayers()) {
        //            if (!player.isReady()) {
        //                System.out.printf("Player %s is not ready\n", player.getPlayerName());
        //                return;
        //            }
        //        }
        this.selectedServer.broadcast(new StartGame());
        this.selectedServer.getGameInstance().start();
    }

    @FXML
    protected void sendMessage() throws IOException {
        if (selectedClient == null) return;
        selectedClient.sendObject(new ServerMessage(sendMessageClient.getText()));
    }

    @FXML
    protected void disconnectClient() throws IOException {
        if (selectedClient == null) return;
        selectedClient.sendObject(
                new CloseServer("You have been forcibly disconnected by the server"));
        selectedClient.close();
    }

    public void test(ActionEvent actionEvent) throws IOException {
        //        selectedClient.sendObject(new Test());
        //        selectedClient.sendObject(
        //                new io.github.haappi.shared.Card("red", "cup", "large", "stripes"));
    }
}