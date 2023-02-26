package io.github.haappi.bold_client.Packets;

public class ServerMessage {
    private final String message;

    public ServerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
