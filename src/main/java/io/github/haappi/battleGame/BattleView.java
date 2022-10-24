package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Opponent;
import io.github.haappi.battleGame.Classes.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import static io.github.haappi.battleGame.Utils.*;

public class BattleView {
    @FXML
    protected Text opponentStats, yourStats, battleLengthTime, recentActions;

    private Opponent opponentInstance;
    private Player playerInstance;

    @FXML
    protected void initialize() {
        Player player = HelloApplication.getInstance().getPlayer();
        String type = getRandomElement(HelloApplication.getInstance().getOpponentNames());
        Opponent opponent = new Opponent.OpponentBuilder(type, type.toLowerCase()).setAttack(randomlySomething(player.getAttack())).setDefense(player.getDefense()).setHealth(randomlySomething(player.getMaxHealth())).build();
        this.opponentInstance = opponent;
        this.playerInstance = player;
    }

    @FXML
    protected void attack(ActionEvent actionEvent) {
        opponentInstance.setHealth(opponentInstance.getHealth() - (playerInstance.getAttack() * playerInstance.getFatigueLevel()));
    }

    @FXML
    protected void openInventory(ActionEvent actionEvent) {
        InventoryView.previousClassFXML = "battle-view";
        HelloApplication.getInstance().setStageScene("inventory-view");
    }

    @FXML
    protected void useItem(ActionEvent actionEvent) {
    }

    @FXML
    protected void defend(ActionEvent actionEvent) {
        this.playerInstance.setDefense(this.playerInstance.getDefense() * getRandomDouble(0, 0.3));
        getTextViewThing(recentActions, "Your defense is now " + this.playerInstance.getDefense() + "!");
    }

    @FXML
    protected void zoomZoomAway(ActionEvent actionEvent) {
        if (playerInstance.getSpeed() * playerInstance.getFatigueLevel() > opponentInstance.getSpeed() * opponentInstance.getFatigueLevel()) {
            HelloApplication.getInstance().setStageScene("main-menu");
        } else {
            getTextViewThing(recentActions, "You can't outrun your opponent!");
        }
    }
}
