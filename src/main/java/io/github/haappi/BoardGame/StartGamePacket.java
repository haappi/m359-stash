package io.github.haappi.BoardGame;

public class StartGamePacket extends BasePacket {
    public StartGamePacket() {
        super(ClassTypes.START_GAME, false, HelloApplication.getInstance().getClientID());
    }
}
