package io.github.haappi.packets;


import java.io.Serial;

public class ServerMessage implements Packet {
    @Serial private static final long serialVersionUID = 928839779677749239L;
    private final String message;

    public ServerMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void handle() {}
}
