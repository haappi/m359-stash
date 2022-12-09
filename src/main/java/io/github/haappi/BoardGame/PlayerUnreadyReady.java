package io.github.haappi.BoardGame;

public class PlayerUnreadyReady extends BasePacket {
    private final boolean isReady;

    public boolean isReady() {
        return isReady;
    }

    public PlayerUnreadyReady(boolean isReady, String clientID) {
        super(ClassTypes.PLAYER_UNREADY_READY, false, 0.0, clientID);
        this.isReady = isReady;
    }
}
