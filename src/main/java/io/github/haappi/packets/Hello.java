package io.github.haappi.packets;


import java.io.Serial;

public class Hello implements Packet {
    @Serial private static final long serialVersionUID = -1653769749542923033L;
    private final String clientName;
    private final String ip;
    private final int port;

    public Hello(String clientName, String ip, int port) {
        this.clientName = clientName;
        this.ip = ip;
        this.port = port;
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
    public void handle() {}
}
