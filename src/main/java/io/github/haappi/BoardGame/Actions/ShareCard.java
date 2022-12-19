package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.*;

import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

public class ShareCard extends Action {
    public ShareCard(PlayerTurn playerTurn) {
        super("Share Card", playerTurn);
        // give a card from your hand to another player.
    }

    @Override
    public void doAction() {
        super.doAction();

        ListView<Player> playerListView = new ListView<>();
        playerListView.getItems().addAll(getPlayer().getGame().getPlayers());
        playerListView.getItems().remove(getPlayer());

        ArrayList<Object> thingz =
                Utils.getTextInput("Share Card", playerListView, " with a player");
        TextInputDialog td = (TextInputDialog) thingz.get(0);
        td.showAndWait();
        Wrapper wrapper = (Wrapper) thingz.get(1);
        Player player = (Player) wrapper.get();

        if (player.getCurrentCity() != getPlayer().getCurrentCity()) {
            super.incrementRemainingActions();
            return;
        }

        ListView<Card> cardListView = new ListView<>();
        cardListView.getItems().addAll(getPlayer().getHand());
        thingz = Utils.getTextInput("Share Card", cardListView, " with " + player.getName());
        td = (TextInputDialog) thingz.get(0);
        td.showAndWait();
        wrapper = (Wrapper) thingz.get(1);
        Card card = (Card) wrapper.get();

        if (getPlayer().getHand().contains(card)) {
            getPlayer().discardACard(card);
            player.addCardToHand(card);
        } else {
            super.incrementRemainingActions();
        }
    }
}
