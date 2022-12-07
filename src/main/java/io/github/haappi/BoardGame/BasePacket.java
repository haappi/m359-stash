package io.github.haappi.BoardGame;

public class BasePacket {
    private final String classType;
    private final boolean ignoreSelf;


    public BasePacket(ClassTypes classTypes, boolean ignoreSelf) {
        this.classType = String.valueOf(classTypes);
        this.ignoreSelf = ignoreSelf;
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
