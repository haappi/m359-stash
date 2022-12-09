package io.github.haappi.BoardGame;

public class UserLeft extends BasePacket {
    private final String UUID;

    public String getUUID() {
        return UUID;
    }

    public UserLeft() {
        super(ClassTypes.USER_LEFT);
        this.UUID = HelloApplication.getInstance().getClientID();
    }
}
