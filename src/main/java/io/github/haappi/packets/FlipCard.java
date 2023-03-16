package io.github.haappi.packets;

import java.io.Serial;

public class FlipCard implements Packet {
    @Serial private static final long serialVersionUID = 6442602443263733511L;
    private final String cardName;
    private final boolean state;

    public FlipCard(String cardName, boolean state) {
        this.cardName = cardName;
        this.state = state;
    }

    public FlipCard(Card card) {
        this.cardName = card.getCardName();
        this.state = card.isFlipped();
    }

    public String getCardName() {
        return cardName;
    }
}
