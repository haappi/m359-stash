package io.github.haappi.BoardGame;

public class RolePickedPacket extends BasePacket {
    private final Roles role;

    public RolePickedPacket(Roles role) {
        super(ClassTypes.ROLE_PICKED);
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }
}
