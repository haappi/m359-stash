package io.github.haappi.BoardGame;

public class LoseScreenPacket extends BasePacket {
    private final String reason;

    public LoseScreenPacket(String reason) {
        super(ClassTypes.LOSE_SCREEN);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
