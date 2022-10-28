package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.HoldableItem;
import io.github.haappi.battleGame.Classes.Opponent;
import io.github.haappi.battleGame.Classes.Player;
import io.github.haappi.battleGame.Classes.Potions;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.github.haappi.battleGame.Utils.*;

public class BattleView {
    private final ArrayList<Button> buttons = new ArrayList<>();
    private final long startTime = System.currentTimeMillis();
    public ListView<InventoryItem> invItems;
    @FXML
    protected Button upShields;
    @FXML
    protected Button useItem;
    @FXML
    protected Button flee;
    @FXML
    protected Button mainMenu;
    @FXML
    protected Text opponentStats, yourStats, battleLengthTime, recentActions;
    private double playerHealthRegained, opponentHealthRegained, playerHealthDepleted, opponentHealthDepleted, playerDamageDealt, opponentDamageDealt, playerDamageTaken, opponentDamageTaken;
    private long endTime;
    private boolean didPlayerWin;
    private Opponent opponentInstance;
    private Player playerInstance;

    @FXML
    protected void initialize() {
        Player player = HelloApplication.getInstance().getPlayer();
        String type = getRandomElement(HelloApplication.getInstance().getOpponentNames());
        Opponent opponent = new Opponent.OpponentBuilder(type, type.toLowerCase()).setAttack(randomlySomething(player.getAttack() + 5)).setDefense(player.getDefense() + 2).setHealth(player.getMaxHealth() + 5).build();
        this.opponentInstance = opponent;
        this.playerInstance = player;
        updateStatsLocally();
        buttons.addAll(new ArrayList<>(List.of(upShields, useItem, flee)));
        mainMenu.setVisible(false);
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
            endTime = System.currentTimeMillis();
            long battleLength = (endTime - startTime) / 1000;
            battleLengthTime.setText("Battle length: " + battleLength + "s");
            recentActions.setText("Battle ended! " + (playerInstance.getCurrentHealth() <= 0.00 ? "You lost!" : "You won!"));
            didPlayerWin = playerInstance.getCurrentHealth() > 0.00;
            if (didPlayerWin) {
                playerInstance.getInventory().add(new Potions.PotionBuilder("Health Potion", 10.00).setAmountGiven(10.00).setStatGiven("health").build());
            }
            for (Button button : buttons) {
                button.setVisible(false);
            }
            mainMenu.setVisible(true);
        }
    }

    @FXML
    protected void attack() {
        System.out.println(playerInstance.getHeldItem());

        double amount = round(reduceByFatigue(playerInstance.getAttack(), playerInstance.getFatigueLevel()));
        opponentInstance.setHealth(getDamageAfterDefense(opponentInstance.getHealth() - amount, opponentInstance.getDefense()));
        setTextString(recentActions, "You attacked " + opponentInstance.getName() + " for " + amount + " damage.");
        opponentHealthDepleted += amount;
        playerDamageDealt += amount;
        opponentDamageTaken += amount;
        increaseFatigue(playerInstance);
        updateStatsLocally();
        opponentTurn();
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
            setTextString(recentActions, "You can't outrun your opponent! Tire them out or run faster!");
        }
        updateStatsLocally();
    }

    private void opponentTurn() {
        switch (getRandomInteger(0, 5)) {
            case 0, 1, 2:
                double amount = round(reduceByFatigue(opponentInstance.getAttack(), opponentInstance.getFatigueLevel()));
                playerInstance.setCurrentHealth(getDamageAfterDefense(playerInstance.getCurrentHealth() - amount, playerInstance.getDefense()));
                setTextString(recentActions, opponentInstance.getName() + " attacked you for " + amount + " damage.");
                playerHealthDepleted += amount;
                opponentDamageDealt += amount;
                playerDamageTaken += amount;
                increaseFatigue(opponentInstance);
                break;
            case 3:
                opponentInstance.setDefense(round(opponentInstance.getDefense() * getRandomDouble(0, 0.3)));
                setTextString(recentActions, opponentInstance.getName() + " defended.");
                break;
            case 5:
                opponentInstance.setHealth(round(opponentInstance.getHealth() * getRandomDouble(0, 0.3)));
                setTextString(recentActions, opponentInstance.getName() + " cheated and healed themselves.");
                break;
        }
        updateStatsLocally();
    }

    public void inventoryItemsRequested(Event event) {
        invItems.getItems().clear();
        for (InventoryItem item : playerInstance.getInventory()) {
            invItems.getItems().add(item);
        }
    }

    public void useInvItem() {
        InventoryItem item = invItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            if (item instanceof Potions potion) {
                if (potion.getStatGiven().equalsIgnoreCase("health")) {
                    playerInstance.setCurrentHealth(playerInstance.getCurrentHealth() + potion.getAmountGiven());
                    playerHealthRegained += potion.getAmountGiven();
                    setTextString(recentActions, "You used a " + potion.getName() + " and gained " + potion.getAmountGiven() + " health.");
                } else if (potion.getStatGiven().equalsIgnoreCase("attack")) {
                    playerInstance.setAttack(playerInstance.getAttack() + potion.getAmountGiven());
                    setTextString(recentActions, "You used a " + potion.getName() + " and gained " + potion.getAmountGiven() + " attack.");
                } else if (potion.getStatGiven().equalsIgnoreCase("speed")) {
                    playerInstance.setSpeed((int) (playerInstance.getSpeed() + potion.getAmountGiven()));
                    setTextString(recentActions, "You used a " + potion.getName() + " and gained " + potion.getAmountGiven() + " speed.");
                } else if (potion.getStatGiven().equalsIgnoreCase("defense")) {
                    playerInstance.setDefense(playerInstance.getDefense() + potion.getAmountGiven());
                    setTextString(recentActions, "You used a " + potion.getName() + " and gained " + potion.getAmountGiven() + " defense.");
                }
            } else {
                setTextString(recentActions, "You equipped " + item + ".");
                invItems.getItems().add(playerInstance.getHeldItem());
                playerInstance.setHeldItem((HoldableItem) item);
            }
            invItems.getItems().remove(item);


            updateStatsLocally();
            opponentTurn();
        } else {
            setTextString(recentActions, "You need to select an item to use!");
        }
    }

    public void mainMenu() throws IOException {
        HelloApplication.getInstance().addBattleToData(new BattleData(didPlayerWin, playerHealthDepleted, playerHealthRegained, playerDamageDealt, playerDamageTaken, opponentHealthDepleted, opponentHealthRegained, opponentDamageDealt, opponentDamageTaken, startTime, endTime));
//        String a = String.valueOf(HelloApplication.getInstance().getBattleData().get(0));
//        ArrayList<String> data = new ArrayList<>(List.of(a.split(": ")));
//        data.remove(0);
//        for (String thing : data) {
//            data.set(data.indexOf(thing), thing.split("\n")[0]);
//        }
//        String[] stringArray = new String[data.size()];
//        for (int i = 0; i < data.size(); i++) {
//            stringArray[i] = data.get(i);
//        }
//        System.out.println(data);
//        data.remove(7);
//        data.remove(11);
//        System.out.println(data);
//        HelloApplication.getInstance().addBattleToData(new BattleData(stringArray));
//
//        a = String.valueOf(HelloApplication.getInstance().getBattleData().get(1));
//        data = new ArrayList<>(List.of(a.split(": ")));
//        data.remove(0);
//        for (String thing : data) {
//            data.set(data.indexOf(thing), thing.split("\n")[0]);
//        }
//        System.out.println(data);
//
        // create file if not wexists

        playerInstance.setCurrentHealth(didPlayerWin ? playerInstance.getMaxHealth() : playerInstance.getMaxHealth() / 2);
        playerInstance.addToBankBalance(didPlayerWin ? 150 : 30);
        HelloApplication.getInstance().setStageScene("main-menu");
    }


}
