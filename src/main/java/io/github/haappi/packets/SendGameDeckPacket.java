package io.github.haappi.packets;

import java.io.Serial;

public class SendGameDeckPacket implements Packet {
    @Serial private static final long serialVersionUID = -4689853371206114546L;
    private final Card[][] cards;

    public SendGameDeckPacket(Card[][] cards) {
        this.cards = cards;
    }

    public Card[][] getCards() {
        return cards;
    }
}
