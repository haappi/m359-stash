package io.github.haappi.BoardGame;

public class LoseScreenPacket extends BasePacket {
    private final String reason;

    public LoseScreenPacket(String reason) {
        super(ClassTypes.LOSE_SCREEN, false);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
