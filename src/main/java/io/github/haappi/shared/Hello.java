package io.github.haappi.shared;

public class Hello implements Packet {
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
}
