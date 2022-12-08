package io.github.haappi.BoardGame;

public class BasePacket {
    private final String classType;
    private final boolean ignoreSelf;
    private final double timeout;

    public double getTimeout() {
        return timeout;
    }

    public BasePacket(ClassTypes classTypes, boolean ignoreSelf, double timeout) {
        this.classType = String.valueOf(classTypes);
        this.ignoreSelf = ignoreSelf;
        this.timeout = timeout;
    }

    public BasePacket(ClassTypes classTypes, boolean ignoreSelf) {
        this(classTypes, ignoreSelf, 0.0);
    }

    public BasePacket(ClassTypes classTypes) {
        this(classTypes, true);
    }

    public BasePacket() {
        this(ClassTypes.UNKNOWN, true);
    }

    public boolean isIgnoreSelf() {
        return ignoreSelf;
    }

    public String getClassType() {
        return this.classType;
    }
}
