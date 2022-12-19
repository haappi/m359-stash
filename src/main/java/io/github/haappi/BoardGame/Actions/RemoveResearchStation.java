package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.PlayerTurn;

public class RemoveResearchStation extends Action {
    public RemoveResearchStation(PlayerTurn playerTurn) {
        super("Remove Research Station", playerTurn);
    }

    @Override
    public void doAction() {
        if (!getPlayer().getCurrentCity().isHasResearchStation()) {
            return;
        }
        if (getPlayer().getCurrentCity().getCityName().equalsIgnoreCase("Atlanta")) {
            return;
        }
        super.doAction();
        if (getPlayer().getCurrentCity().isHasResearchStation()) {
            getPlayer().getCurrentCity().setHasResearchStation(false);
        } else {
            super.incrementRemainingActions();
        }
    }
}
