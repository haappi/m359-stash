package io.github.haappi.packets;

import io.github.haappi.bold_client.Client;

public class Ready implements Packet {
    private final String clientName;

    public Ready() {
        this.clientName = Client.getInstance().clientName();
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public void handle() {

    }
}
