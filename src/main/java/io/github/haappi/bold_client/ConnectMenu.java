package io.github.haappi.bold_client;

import io.github.haappi.packets.Ready;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConnectMenu {
    public Button readyButton;
    public Button joinButton;
    public Label status1;
    @FXML protected TextField clientName;
    @FXML protected TextField bindIP;
    @FXML protected TextField bindPort;
    @FXML protected Label status;

    @FXML
    protected void joinServer() {
        final int port = bindPort.getText().isEmpty() ? 2005 : Integer.parseInt(bindPort.getText());
        final String ip = bindIP.getText().isEmpty() ? "localhost" : bindIP.getText();

        status.setText("Connecting... to " + ip + ":" + port + "");
        joinButton.setDisable(true);
        Client client = Client.getInstance(clientName.getText());
        try {
            client.connect(ip, port);
        } catch (IOException e) {
            status.setText("Failed to connect! Is the server running?");
            readyButton.setDisable(true);
            joinButton.setDisable(false);
            client.reset();
            return;
        }
        readyButton.setDisable(false);
        joinButton.setDisable(true);
        status.setText("Connected to " + ip + ":" + port + "");
        status1.setText("You are NOT ready.");
    }

    public void readyButtonClick(ActionEvent actionEvent) throws IOException {
        status1.setText("You are ready.");
        Client.getInstance().sendObject(new Ready());
    }
}
