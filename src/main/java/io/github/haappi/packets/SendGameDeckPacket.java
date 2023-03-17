package io.github.haappi.packets;

import io.github.haappi.bold_client.GameView;

import java.io.Serial;

public class SendGameDeckPacket implements Packet {
    @Serial
    private static final long serialVersionUID = -4689853371206114546L;
    private final Card[][] cards;

    public SendGameDeckPacket(Card[][] cards) {
        this.cards = cards;
    }

    public Card[][] getCards() {
        return cards;
    }

    @Override
    public void handle() {
        GameView instance = GameView.getInstance();
        instance.cards = cards;
        instance.updateCards(cards);
    }
}
