package io.github.haappi.BoardGame;

public class PlayerUnreadyReady extends BasePacket {
    private final boolean isReady;

    public PlayerUnreadyReady(boolean isReady) {
        super(ClassTypes.PLAYER_UNREADY_READY);
        this.isReady = isReady;
    }
}
