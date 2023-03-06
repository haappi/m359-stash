package io.github.haappi.packets;

import java.io.Serial;

public class FlipCard implements Packet {
    @Serial private static final long serialVersionUID = 6442602443263733511L;
    private final String cardName;

    public FlipCard(String cardName) {
        this.cardName = cardName;
    }

    public FlipCard(Card card) {
        this.cardName = card.getCardName();
    }

    public String getCardName() {
        return cardName;
    }
}
