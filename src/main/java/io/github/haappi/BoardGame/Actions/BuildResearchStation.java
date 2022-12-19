package io.github.haappi.BoardGame.Actions;

import io.github.haappi.BoardGame.City;
import io.github.haappi.BoardGame.PlayerTurn;

public class BuildResearchStation extends Action {
    public BuildResearchStation(PlayerTurn playerTurn) {
        super("Build Research Station", playerTurn);
        // build a research station in your current city. discords current city card.
    }

    @Override
    public void doAction() {
        if (getPlayer().getCurrentCity().isHasResearchStation()) {
            return;
        }
        int count = 0;
        for (City city : getPlayer().getGame().getCities()) {
            if (city.isHasResearchStation()) {
                count++;
            }
        }
        if (count == 6) {
            return;
        }
        super.doAction();
        if (getPlayer().playerHasCityCard(getPlayer().getCurrentCity())) {
            getPlayer().discardACard(getPlayer().getCityCard(getPlayer().getCurrentCity()));
            getPlayer().getCurrentCity().setHasResearchStation(true);
        } else {
            super.incrementRemainingActions();
        }
    }
}
