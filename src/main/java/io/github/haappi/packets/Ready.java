package io.github.haappi.packets;

import io.github.haappi.bold_client.Client;

import java.io.Serial;

public class Ready implements Packet {
    @Serial
    private static final long serialVersionUID = -5058600322899240319L;
    private final Player player;

    public Ready() {
        this.player = Player.getInstance();
        System.out.println(this.player);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void handle() {}
}
