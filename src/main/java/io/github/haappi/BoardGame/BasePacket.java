package io.github.haappi.BoardGame;

public class BasePacket {
    private final String classType;
    private final boolean ignoreSelf;
    private final double timeout;
    private final String clientID;
    private final long ping;

    public BasePacket(ClassTypes classTypes, boolean ignoreSelf, double timeout, String clientID) {
        this.classType = String.valueOf(classTypes);
        this.ignoreSelf = ignoreSelf;
        this.timeout = timeout;
        this.clientID = clientID;
        this.ping = Utils.getPing();
    }

    public BasePacket(ClassTypes classTypes, boolean ignoreSelf, String clientID) {
        this(classTypes, ignoreSelf, 0.0, clientID);
    }

    public BasePacket(ClassTypes classTypes) {
        this(classTypes, true, 0.0, HelloApplication.getInstance().getClientID());
    }

    public BasePacket() {
        this(ClassTypes.UNKNOWN, true, 0.0, HelloApplication.getInstance().getClientID());
    }

    public long getPing() {
        return ping;
    }

    public double getTimeout() {
        return timeout;
    }

    public String getClientID() {
        return clientID;
    }

    public String getUUID() {
        return clientID;
    }

    public boolean isIgnoreSelf() {
        return ignoreSelf;
    }

    public String getClassType() {
        return this.classType;
    }
}
