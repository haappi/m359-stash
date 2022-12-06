package io.github.haappi.BoardGame;

public class ConnectedUser extends BasePacket {
    private final String UUID;
    private final long connectedSince;
    private final String userName;

    public ConnectedUser(String UUID, String userName) {
        super(ClassTypes.CONNECTED_USER);
        this.UUID = UUID;
        this.userName = userName;
        this.connectedSince = System.currentTimeMillis();
    }

    public ConnectedUser(String userName) {
        super(ClassTypes.CONNECTED_USER);
        this.UUID = HelloApplication.getInstance().getClientID();
        this.userName = userName;
        this.connectedSince = System.currentTimeMillis();
    }

    public String toString() {
        return this.userName; // add ready / not ready status here.
    }
}
