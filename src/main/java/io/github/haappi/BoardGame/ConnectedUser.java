package io.github.haappi.BoardGame;

public class ConnectedUser extends BasePacket implements Comparable<ConnectedUser> {
    // https://stackoverflow.com/questions/44355842/cannot-cast-to-java-lang-comparable
    private final String UUID;
    private final long connectedSince;
    private final String userName;
    private boolean isReady;
    private boolean isHost;

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

    @Override
    public String getUUID() {
        return UUID;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
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

    public int compareTo(ConnectedUser o) {
        return this.userName.compareTo(o.getUserName());
    }
}
