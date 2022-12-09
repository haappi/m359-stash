package io.github.haappi.BoardGame;

public class ConnectedUser extends BasePacket {
    private final String UUID;
    private final long connectedSince;
    private final String userName;
    private boolean isReady;

    public ConnectedUser(String UUID, String userName, boolean ignoreSelf) {
        super(ClassTypes.CONNECTED_USER, ignoreSelf, 0.1, UUID);
        this.UUID = UUID;
        this.userName = userName;
        this.connectedSince = System.currentTimeMillis();
    }

    public ConnectedUser(String UUID, String userName) {
        super(ClassTypes.CONNECTED_USER, true, 0.1, UUID);
        this.UUID = UUID;
        this.userName = userName;
        this.connectedSince = System.currentTimeMillis();
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public String getUserName() {
        return userName;
    }

    public String toString() {
        return this.userName
                + (this.isReady ? " (R) " : " (NR) ")
                + super.getPing(); // add ready / not ready status here.
    }
}
