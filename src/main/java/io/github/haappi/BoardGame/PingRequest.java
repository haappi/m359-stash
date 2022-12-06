package io.github.haappi.BoardGame;

public class PingRequest extends BasePacket {
    private final long pingTime;

    public PingRequest() {
        super(ClassTypes.PING_REQUEST);
        this.pingTime = Utils.getPing();
    }

    public long getPingTime() {
        return pingTime;
    }
}
