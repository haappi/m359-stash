package io.github.haappi.packets;

import io.github.haappi.shared.Enums;

import java.io.Serial;

public class CustomCard extends Card{
    @Serial
    private static final long serialVersionUID = 4935755016160238078L;

    public CustomCard(Enums size, Enums color, Enums container, Enums pattern) {
        super(size, color, container, pattern);
    }
}
