package io.github.haappi.BoardGame;

public class NewPlayerJoin extends BasePacket {
    private final String UUID;
    private final String userName;

    public NewPlayerJoin(String UUID, String userName) {
        super(ClassTypes.NEW_PLAYER_JOIN);
        this.UUID = UUID;
        this.userName = userName;
    }

    public NewPlayerJoin(String userName) {
        super(ClassTypes.NEW_PLAYER_JOIN);
        this.UUID = HelloApplication.getInstance().getClientID();
        this.userName = userName;
    }

    public String getUUID() {
        return UUID;
    }

    public String getUserName() {
        return userName;
    }
}
