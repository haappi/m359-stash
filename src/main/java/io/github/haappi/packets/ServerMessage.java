package io.github.haappi.packets;

public class ServerMessage implements Packet {
    private final String message;

    public ServerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
