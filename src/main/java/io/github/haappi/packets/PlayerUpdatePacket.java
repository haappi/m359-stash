package io.github.haappi.packets;

import java.io.Serial;
import java.io.Serializable;

public class PlayerUpdatePacket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1944636289472452727L;
    private final Player player;

    public PlayerUpdatePacket(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
