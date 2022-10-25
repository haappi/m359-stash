package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Opponent;
import io.github.haappi.battleGame.Classes.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static io.github.haappi.battleGame.Utils.*;

public class BattleView {
    @FXML
    protected Text opponentStats, yourStats, battleLengthTime, recentActions;
    private double playerHealthRegained, opponentHealthRegained, playerHealthDepleted, opponentHealthDepleted, playerDamageDealt, opponentDamageDealt, playerDamageTaken, opponentDamageTaken;
    private long battleLength;
    private final ArrayList<String> playerItemsUsed = new ArrayList<>();
    private final ArrayList<String> opponentItemsUsed = new ArrayList<>();

    private Opponent opponentInstance;
    private Player playerInstance;

    @FXML
    protected void initialize() {
        Player player = HelloApplication.getInstance().getPlayer();
        String type = getRandomElement(HelloApplication.getInstance().getOpponentNames());
        Opponent opponent = new Opponent.OpponentBuilder(type, type.toLowerCase()).setAttack(randomlySomething(player.getAttack())).setDefense(player.getDefense()).setHealth(randomlySomething(player.getMaxHealth())).build();
        this.opponentInstance = opponent;
        this.playerInstance = player;
        updateStatsLocally();
    }

    private void updateStatsLocally() {
        playerInstance.setDefense(round(playerInstance.getDefense()));
        playerInstance.setAttack(round(playerInstance.getAttack()));
        playerInstance.setCurrentHealth(round(playerInstance.getCurrentHealth()));
        playerInstance.setMaxHealth(round(playerInstance.getMaxHealth()));
        playerInstance.setFatigueLevel(round(playerInstance.getFatigueLevel()));

        yourStats.setText(playerInstance.getPlayerDataAsString());

        opponentInstance.setDefense(round(opponentInstance.getDefense()));
        opponentInstance.setAttack(round(opponentInstance.getAttack()));
        opponentInstance.setHealth(round(opponentInstance.getHealth()));
        opponentInstance.setMaxHealth(round(opponentInstance.getMaxHealth()));
        opponentInstance.setFatigueLevel(round(opponentInstance.getFatigueLevel()));

        opponentStats.setText(opponentInstance.getOpponentDataAsString());

        if (playerInstance.getCurrentHealth() <= 0.00 || opponentInstance.getHealth() <= 0.00) {
            recentActions.setText("looks like something happened");
        }
    }

    @FXML
    protected void attack(ActionEvent actionEvent) {
        opponentInstance.setHealth(opponentInstance.getHealth() - reduceByFatigue(playerInstance.getAttack(), playerInstance.getFatigueLevel()));
        getTextViewThing(recentActions, "You attacked " + opponentInstance.getName() + " for " + reduceByFatigue(playerInstance.getAttack(), playerInstance.getFatigueLevel()) + " damage.");
        increaseFatigue(playerInstance);
        opponentTurn();
        updateStatsLocally();
    }

    @FXML
    protected void openInventory(ActionEvent actionEvent) {
        // todo make a tabpane or something with the itemsd that the player bothered dragging along with them else i would lose the reference to the opponent instances
//        InventoryView.previousClassFXML = "battle-view";
//        HelloApplication.getInstance().setStageScene("inventory-view");
    }

    @FXML
    protected void useItem(ActionEvent actionEvent) {
        updateStatsLocally();
    }

    @FXML
    protected void defend(ActionEvent actionEvent) {
        this.playerInstance.setDefense(this.playerInstance.getDefense() * getRandomDouble(0, 0.3));
        updateStatsLocally();
    }

    @FXML
    protected void zoomZoomAway(ActionEvent actionEvent) {
        if (reduceByFatigue(playerInstance.getSpeed(), playerInstance.getFatigueLevel()) > reduceByFatigue(opponentInstance.getSpeed(), opponentInstance.getFatigueLevel())) {
            HelloApplication.getInstance().setStageScene("main-menu");
        } else {
            getTextViewThing(recentActions, "You can't outrun your opponent! Tire them out or run faster!");
        }
        updateStatsLocally();
    }

    private void opponentTurn() {
        switch (getRandomInteger(0, 2)) {
            case 0:
                playerInstance.setCurrentHealth(playerInstance.getCurrentHealth() - reduceByFatigue(opponentInstance.getAttack(), opponentInstance.getFatigueLevel()));
                getTextViewThing(recentActions, opponentInstance.getName() + " attacked you for " + reduceByFatigue(opponentInstance.getAttack(), opponentInstance.getFatigueLevel()) + " damage.");
                increaseFatigue(opponentInstance);
                break;
            case 1:
                opponentInstance.setDefense(opponentInstance.getDefense() * getRandomDouble(0, 0.3));
                getTextViewThing(recentActions, opponentInstance.getName() + " defended.");
                break;
            case 2:
        }

    }
}
