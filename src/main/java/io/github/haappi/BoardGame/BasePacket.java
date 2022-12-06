package io.github.haappi.BoardGame;

public class BasePacket {
    private final String classType;
    private boolean isAMovePacket = false;
    private String fxID = "nil";

    public BasePacket(ClassTypes classTypes) {
        this.classType = String.valueOf(classTypes);
    }

    public BasePacket() {
        this.classType = String.valueOf(ClassTypes.UNKNOWN);
    }

    public String getClassType() {
        return this.classType;
    }

    public boolean isMovePacket() {
        return isAMovePacket;
    }

    public void setMovePacket(boolean movePacket) {
        isAMovePacket = movePacket;
    }

    public String getFxID() {
        return fxID;
    }

    public void setFxID(String fxID) {
        this.fxID = fxID;
    }
}
