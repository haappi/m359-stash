package io.github.haappi.BoardGame;

public class Test extends BasePacket {
    private final String name;
    private final Long creationTime;

    public Test(String name) {
        super(ClassTypes.TEST);
        this.name = name;
        this.creationTime = System.currentTimeMillis();
    }

    public String getName() {
        return this.name;
    }
}
