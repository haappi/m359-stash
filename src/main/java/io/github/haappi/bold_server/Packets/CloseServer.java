package io.github.haappi.bold_server.Packets;

import java.io.Serializable;

public class CloseServer implements Serializable {
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
