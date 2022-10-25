package io.github.haappi.battleGame;

import java.util.ArrayList;
import java.util.UUID;

public class BattleData {
    private final boolean didPlayerWin;
    private final Long battleLength;
    private final UUID battleId = UUID.randomUUID();

    private final double playerHealthDepleted;
    private final double playerHealthRegained;
    private final double playerManaDepleted;
    private final double playerManaRegained;
    private final double playerDamageDealt;
    private final double playerDamageTaken;
    private final ArrayList<InventoryItem> itemsUsed = new ArrayList<>();

    private final double enemyHealthDepleted;
    private final double enemyHealthRegained;
    private final double enemyManaDepleted;
    private final double enemyManaRegained;
    private final double enemyDamageDealt;
    private final double enemyDamageTaken;
    private final ArrayList<InventoryItem> enemyItemsUsed = new ArrayList<>();

    public BattleData(boolean didPlayerWin, double playerHealthDepleted, double playerHealthRegained, double playerManaDepleted, double playerManaRegained, double playerDamageDealt, double playerDamageTaken, ArrayList<InventoryItem> itemsUsed, double enemyHealthDepleted, double enemyHealthRegained, double enemyManaDepleted, double enemyManaRegained, double enemyDamageDealt, double enemyDamageTaken, ArrayList<InventoryItem> enemyItemsUsed, Long startTime, Long endTime) {
        this.didPlayerWin = didPlayerWin;
        this.playerHealthDepleted = playerHealthDepleted;
        this.playerHealthRegained = playerHealthRegained;
        this.playerManaDepleted = playerManaDepleted;
        this.playerManaRegained = playerManaRegained;
        this.playerDamageDealt = playerDamageDealt;
        this.playerDamageTaken = playerDamageTaken;
        this.itemsUsed.addAll(itemsUsed);
        this.enemyHealthDepleted = enemyHealthDepleted;
        this.enemyHealthRegained = enemyHealthRegained;
        this.enemyManaDepleted = enemyManaDepleted;
        this.enemyManaRegained = enemyManaRegained;
        this.enemyDamageDealt = enemyDamageDealt;
        this.enemyDamageTaken = enemyDamageTaken;
        this.enemyItemsUsed.addAll(enemyItemsUsed);
        this.battleLength = (endTime - startTime) / 1000;
    }

    public BattleData(String data) {
        // construct a battle data class with string data
    }

    public boolean isDidPlayerWin() {
        return didPlayerWin;
    }

    public double getPlayerHealthDepleted() {
        return playerHealthDepleted;
    }

    public double getPlayerHealthRegained() {
        return playerHealthRegained;
    }

    public double getPlayerManaDepleted() {
        return playerManaDepleted;
    }

    public double getPlayerManaRegained() {
        return playerManaRegained;
    }

    public double getPlayerDamageDealt() {
        return playerDamageDealt;
    }

    public double getPlayerDamageTaken() {
        return playerDamageTaken;
    }

    public ArrayList<InventoryItem> getItemsUsed() {
        return itemsUsed;
    }

    public double getEnemyHealthDepleted() {
        return enemyHealthDepleted;
    }

    public double getEnemyHealthRegained() {
        return enemyHealthRegained;
    }

    public double getEnemyManaDepleted() {
        return enemyManaDepleted;
    }

    public double getEnemyManaRegained() {
        return enemyManaRegained;
    }

    public double getEnemyDamageDealt() {
        return enemyDamageDealt;
    }

    public double getEnemyDamageTaken() {
        return enemyDamageTaken;
    }

    public ArrayList<InventoryItem> getEnemyItemsUsed() {
        return enemyItemsUsed;
    }

    public String toString() {
        // todo add all battle stats in here
    }
}
