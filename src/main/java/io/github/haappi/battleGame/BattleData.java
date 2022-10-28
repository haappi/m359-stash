package io.github.haappi.battleGame;

import java.util.UUID;

public class BattleData {
    private final boolean didPlayerWin;
    private final Long battleLength;
    private final UUID battleId;

    private final double playerHealthDepleted;
    private final double playerHealthRegained;
    private final double playerDamageDealt;
    private final double playerDamageTaken;
    private final double enemyHealthDepleted;
    private final double enemyHealthRegained;
    private final double enemyDamageDealt;
    private final double enemyDamageTaken;

    public BattleData(boolean didPlayerWin, double playerHealthDepleted, double playerHealthRegained, double playerDamageDealt, double playerDamageTaken, double enemyHealthDepleted, double enemyHealthRegained, double enemyDamageDealt, double enemyDamageTaken, Long startTime, Long endTime) {
        this.didPlayerWin = didPlayerWin;
        this.playerHealthDepleted = playerHealthDepleted;
        this.playerHealthRegained = playerHealthRegained;
        this.playerDamageDealt = playerDamageDealt;
        this.playerDamageTaken = playerDamageTaken;
        this.enemyHealthDepleted = enemyHealthDepleted;
        this.enemyHealthRegained = enemyHealthRegained;
        this.enemyDamageDealt = enemyDamageDealt;
        this.enemyDamageTaken = enemyDamageTaken;
        this.battleLength = (endTime - startTime) / 1000;
        this.battleId = UUID.randomUUID();
    }

    public BattleData(String[] data) {
        this.battleId = UUID.fromString(data[0]);
        this.didPlayerWin = Boolean.parseBoolean(data[1]);
        this.battleLength = Long.parseLong(data[2]);
        this.playerHealthDepleted = Double.parseDouble(data[3]);
        this.playerHealthRegained = Double.parseDouble(data[4]);
        this.playerDamageDealt = Double.parseDouble(data[5]);
        this.playerDamageTaken = Double.parseDouble(data[6]);
        this.enemyHealthDepleted = Double.parseDouble(data[7]);
        this.enemyHealthRegained = Double.parseDouble(data[8]);
        this.enemyDamageDealt = Double.parseDouble(data[9]);
        this.enemyDamageTaken = Double.parseDouble(data[10]);
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

    public double getPlayerDamageDealt() {
        return playerDamageDealt;
    }

    public double getPlayerDamageTaken() {
        return playerDamageTaken;
    }


    public double getEnemyHealthDepleted() {
        return enemyHealthDepleted;
    }

    public double getEnemyHealthRegained() {
        return enemyHealthRegained;
    }

    public double getEnemyDamageDealt() {
        return enemyDamageDealt;
    }

    public double getEnemyDamageTaken() {
        return enemyDamageTaken;
    }


    public String toString() {
        return "\nBattleID: " + battleId + "\nDid Player Win: " + didPlayerWin + "\nBattle Length: " + battleLength + "\nPlayer Health Depleted: " + playerHealthDepleted + "\nPlayer Health Regained: " + playerHealthRegained + "\nPlayer Damage Dealt: " + playerDamageDealt + "\nPlayer Damage Taken: " + playerDamageTaken + "\nEnemy Health Depleted: " + enemyHealthDepleted + "\nEnemy Health Regained: " + enemyHealthRegained + "\nEnemy Damage Dealt: " + enemyDamageDealt + "\nEnemy Damage Taken: " + enemyDamageTaken + "\n";
    }
}
