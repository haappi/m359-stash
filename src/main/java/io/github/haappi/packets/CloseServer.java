package io.github.haappi.packets;

public class CloseServer implements Packet {
    private final String message;

    public CloseServer(String message) {
        this.message = message;
    }

    public CloseServer() {
        this.message = "Server is shutting down";
    }

    public String getMessage() {
        return message;
    }
}
