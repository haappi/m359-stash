package io.github.haappi.BoardGame;

public class BasePacket {
    private boolean isAMovePacket = false;
    private String fxID = "nil";
    private final String classType;

    public String getClassType() {
        return this.classType;
    }

    public BasePacket(ClassTypes classTypes) {
        this.classType = String.valueOf(classTypes);
    }

    public BasePacket() {
        this.classType = String.valueOf(ClassTypes.UNKNOWN);
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
