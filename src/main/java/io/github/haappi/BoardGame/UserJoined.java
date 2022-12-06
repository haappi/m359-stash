package io.github.haappi.BoardGame;

public class UserJoined extends BasePacket {
    private final String userName;
    private final long timeStamp;

    public UserJoined(String userName) {
        this.userName = userName;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getUserName() {
        return userName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
