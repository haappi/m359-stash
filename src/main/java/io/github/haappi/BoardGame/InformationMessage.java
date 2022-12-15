package io.github.haappi.BoardGame;

public class InformationMessage extends BasePacket {
    private final String message;

    public InformationMessage(String message) {
        super(ClassTypes.INFORMATION_MESSAGE);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
