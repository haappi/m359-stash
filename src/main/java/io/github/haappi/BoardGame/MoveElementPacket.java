package io.github.haappi.BoardGame;

import javafx.scene.Node;

public class MoveElementPacket extends BasePacket {
    private final String fxID;
    private final double x;
    private final double y;

    public MoveElementPacket(String fxID, double x, double y) {
        super(ClassTypes.MOVE_ELEMENT);
        this.fxID = fxID;
        this.x = x;
        this.y = y;
    }

    public MoveElementPacket(Node node) {
        super(ClassTypes.MOVE_ELEMENT);
        this.fxID = node.getId() == null ? "nil" : node.getId();
        this.x = node.getLayoutX();
        this.y = node.getLayoutY();
    }

    @Override
    public String getFxID() {
        return fxID;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
