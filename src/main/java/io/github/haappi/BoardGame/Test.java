package io.github.haappi.BoardGame;

public class Test extends BasePacket {
    private final String name;
    private final Long creationTime;
    private final String classType = String.valueOf(ClassTypes.TEST);

    public Test(String name) {
        this.name = name;
        this.creationTime = System.currentTimeMillis();
    }

    public String getName() {
        return this.name;
    }
}
