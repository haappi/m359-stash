package io.github.haappi.BoardGame;

public class GameWonPacket extends BasePacket {
    private final String reason;

    public GameWonPacket(String reason) {
        super(ClassTypes.GAME_WON);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
