package io.github.haappi.bold_client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConnectMenu {
    @FXML
    protected TextField clientName;
    @FXML
    protected TextField bindIP;
    @FXML
    protected TextField bindPort;
    @FXML
    protected Label status;

    @FXML
    protected void joinServer() {
        final int port = bindPort.getText().isEmpty() ? 2005 : Integer.parseInt(bindPort.getText());
        final String ip = bindIP.getText().isEmpty() ? "localhost" : bindIP.getText();

        status.setText("Connecting... to " + ip + ":" + port + "");
        Client client = new Client(clientName.getText());
        try {
            client.connect(ip, port);
        } catch (IOException e) {
            status.setText("Failed to connect! Is the server running?");
            return;
        }
        status.setText("Connected to " + ip + ":" + port + "");

    }
}
