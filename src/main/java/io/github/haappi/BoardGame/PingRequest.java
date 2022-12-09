package io.github.haappi.BoardGame;

public class PingRequest extends BasePacket {
    private final long pingTime;

    public PingRequest(String UUID) {
        super(ClassTypes.PING_REQUEST, false, 0.0, UUID);
        this.pingTime = super.getPing();
    }

    public long getPingTime() {
        return pingTime;
    }
}
