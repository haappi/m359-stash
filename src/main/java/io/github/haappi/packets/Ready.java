package io.github.haappi.packets;

import io.github.haappi.bold_client.Client;
import io.github.haappi.packets.Packet;

public class Ready implements Packet {
    private final String clientName;

    public Ready() {
        this.clientName = Client.getInstance().clientName();
    }

    public String getClientName() {
        return clientName;
    }
}
