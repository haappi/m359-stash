package io.github.haappi.BoardGame;

public class GameLost extends BasePacket {
    private final String reason;

    public GameLost(String reason, String clientID) {
        super(ClassTypes.GAME_LOST, false, 0.0, clientID);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
