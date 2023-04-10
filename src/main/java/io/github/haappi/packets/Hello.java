package io.github.haappi.packets;

import io.github.haappi.bold_server.ClientHandler;
import io.github.haappi.bold_server.Server;

import java.io.Serial;

public class Hello implements Packet {
    @Serial
    private static final long serialVersionUID = -1653769749542923033L;
    private final String clientName;
    private final String ip;
    private final int port;

    private final String connectedTo;

    public Hello(String clientName, String ip, int port, String connectedTo) {
        this.clientName = clientName;
        this.ip = ip;
        this.port = port;
        this.connectedTo = connectedTo;
    }

    public String getClientName() {
        return clientName;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public void handle(ClientHandler client) {
        Server server = client.getServer();
//        server.getGameInstance().addPlayer(new Player(clientName));
    }
}
