package io.github.haappi.packets;

import java.io.Serial;

public class CloseServer implements Packet {
    @Serial private static final long serialVersionUID = 8527554074055883126L;
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
