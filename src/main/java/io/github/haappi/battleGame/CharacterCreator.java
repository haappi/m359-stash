package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Objects;

public class CharacterCreator {
    private final double multiplier = ((double) (int) (Math.random() * 100)) / 100;
    @FXML
    protected Text specificClassInformation;
    @FXML
    protected TextField charNameMaker;
    @FXML
    protected Text basicInformation;
    @FXML
    protected MenuButton changePlayerType;
    @FXML
    protected ListView<String> statsView;
    @FXML
    protected Text textPointAmount;
    @FXML
    protected Text pointAmount;
    private Integer selectedIndex = 0;
    private Integer pointsLeft = 12;
    private String characterName = "Timmy";
    private int MINIMUM_HEALTH = 20; // these need to be changed for the different classes
    private int MINIMUM_ATTACK = 5;
    private int MINIMUM_DEFENSE = 5;
    private int MINIMUM_SPEED = 2;
    private int MINIMUM_MANA = 10;
    private int MINIMUM_LUCK = 2;
    private String selectedStat = "Health";
    private String selectedClass = "Fighter";


    @FXML
    protected void initialize() {
        pointAmount.setText(pointsLeft.toString());
        textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft)); // todo fix this overlapping weirdly
        // fixme prolly just change the final x position of it
        statsView.getItems().add("Health: " + MINIMUM_HEALTH);
        statsView.getItems().add("Attack: " + MINIMUM_ATTACK);
        statsView.getItems().add("Defense: " + MINIMUM_DEFENSE);
        statsView.getItems().add("Speed: " + MINIMUM_SPEED);
        statsView.getItems().add("Mana: " + MINIMUM_MANA);
        statsView.getItems().add("Luck: " + MINIMUM_LUCK);
        statsView.getSelectionModel().select(selectedIndex);
    }

    @FXML
    protected void increase() {
        if (pointsLeft > 0) {
            pointsLeft--;
            pointAmount.setText(pointsLeft.toString());
            textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
            switch (selectedStat) {
                case "Health":
                    int currentAmount = Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]);
                    statsView.getItems().set(0, "Health: " + (currentAmount + 1));
                    break;
                case "Attack":
                    currentAmount = Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]);
                    statsView.getItems().set(1, "Attack: " + (currentAmount + 1));
                    break;
                case "Defense":
                    currentAmount = Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]);
                    statsView.getItems().set(2, "Defense: " + (currentAmount + 1));
                    break;
                case "Speed":
                    currentAmount = Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]);
                    statsView.getItems().set(3, "Speed: " + (currentAmount + 1));
                    break;
                case "Mana":
                    currentAmount = Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]);
                    statsView.getItems().set(4, "Mana: " + (currentAmount + 1));
                    break;
                case "Luck":
                    currentAmount = Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]);
                    statsView.getItems().set(5, "Luck: " + (currentAmount + 1));
                    break;
            }
        }
    }

    @FXML
    protected void decrease() {
        switch (selectedStat.toLowerCase()) {
            case "health" -> decreaseFromStat("Health", selectedIndex, MINIMUM_HEALTH);
            case "attack" -> decreaseFromStat("Attack", selectedIndex, MINIMUM_ATTACK);
            case "defense" -> decreaseFromStat("Defense", selectedIndex, MINIMUM_DEFENSE);
            case "speed" -> decreaseFromStat("Speed", selectedIndex, MINIMUM_SPEED);
            case "mana" -> decreaseFromStat("Mana", selectedIndex, MINIMUM_MANA);
            case "luck" -> decreaseFromStat("Luck", selectedIndex, MINIMUM_LUCK);
        }
    }

    private void decreaseFromStat(String statType, int index, int minimumAmount) {
        if (minimumAmount < Integer.parseInt(statsView.getItems().get(index).split(": ")[1])) {
            statsView.getItems().set(index, statType + ": " + (Integer.parseInt(statsView.getItems().get(index).split(": ")[1]) - 1));
            pointsLeft++;
            pointAmount.setText(pointsLeft.toString());
            textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
        }
    }

    @FXML
    protected void finished() {
        Player.PlayerBuilder playerBuilder = new Player.PlayerBuilder(this.characterName).setClazz(this.selectedClass);
        for (String thing : this.statsView.getItems()) {
            playerBuilder = this.builder(playerBuilder, thing);
        }
        Player player = new Player(playerBuilder);
        System.out.println(player.getPlayerDataAsString());
    }

    private Player.PlayerBuilder builder(Player.PlayerBuilder current, String stat) {
        String statName = stat.split(": ")[0];
        int statAmount = Integer.parseInt(stat.split(": ")[1]);

        switch (statName.toLowerCase()) {
            case "health" -> current = current.setMaxHealth(statAmount + (statAmount * multiplier));
            case "attack" -> current = current.setAttack(statAmount + (statAmount * multiplier));
            case "defense" -> current = current.setDefense(statAmount + (statAmount * multiplier));
            case "speed" -> current = current.setSpeed((int) (statAmount + (statAmount * multiplier)));
            case "mana" -> current = current.setMaxMana((int) (statAmount + (statAmount * multiplier)));
            case "luck" -> current = current.setLuck((int) (statAmount + (statAmount * multiplier)));
        }
        return current;
    }

    @FXML
    protected void charNameMaker() {
        System.out.println(charNameMaker.getText());
        characterName = !Objects.equals(charNameMaker.getText(), "") ? charNameMaker.getText() : "Timmy"; // object equals is just equals but doesn't behave weirdly with null
        this.basicInformation.setText(String.format("Name: %s. Class: %s", this.characterName, this.selectedClass));
    }

    @FXML
    protected void onStatChange(MouseEvent mouseEvent) {
        ListView<?> source = (ListView<?>) mouseEvent.getSource();
        String string = (String) source.getSelectionModel().getSelectedItem();
        selectedStat = string.split(":")[0];
        selectedIndex = source.getSelectionModel().getSelectedIndex();
    }

    @FXML
    protected void onClassChange(ActionEvent actionEvent) {
        String selected = ((MenuItem) actionEvent.getSource()).getText().toLowerCase();
        switch (selected) {
            case "ranger" -> { // todo change the minimums for each class dynamically
                specificClassInformation.setText("Rangers are fast, but have low defense and health.");
                selectedClass = "Ranger";
                changeMinimumStats(13, 3, 3, 5, 10, 3);
            }
            case "mage" -> {
                specificClassInformation.setText("Mages are powerful, have high mana and luck, but have low defense and health.");
                selectedClass = "Mage";
                changeMinimumStats(12, 3, 3, 3, 17, 5);
            }
            case "fighter" -> {
                specificClassInformation.setText("Fighters are strong and have high defense, but have low speed and mana.");
                selectedClass = "Fighter";
                changeMinimumStats(20, 5, 5, 1, 7, 2);
            }
            case "assassin" -> {
                specificClassInformation.setText("Assassins are fast and have high attack, but have low defense and health.");
                selectedClass = "Assassin";
                changeMinimumStats(13, 5, 2, 5, 10, 3);
            }
            case "berserker" -> {
                specificClassInformation.setText("Berserkers are strong and have high defense, but have low speed and mana.");
                selectedClass = "Berserker";
                changeMinimumStats(20, 5, 5, 1, 6, 2);
            }
            case "archer" -> {
                specificClassInformation.setText("Archers are fast and agile, but have low defense and health.");
                selectedClass = "Archer";
                changeMinimumStats(12, 3, 3, 5, 10, 3);
            }
        }
        this.basicInformation.setText(String.format("Name: %s. Class: %s", this.characterName, this.selectedClass));
        int length = (int) (this.specificClassInformation.getLayoutX() / 2);
        String setText = specificClassInformation.getText();
        StringBuilder sb = new StringBuilder();
        int currentIndex = 0;
        for (char character : setText.toCharArray()) {
            if (currentIndex >= length) {
                sb.append("\n");
                currentIndex = 0;
            }
            sb.append(character);
            currentIndex++;
        }
        specificClassInformation.setText(sb.toString());
    }

    private void changeMinimumStats(int health, int attack, int defense, int speed, int mana, int luck) {
        MINIMUM_HEALTH = health;
        MINIMUM_ATTACK = attack;
        MINIMUM_DEFENSE = defense;
        MINIMUM_SPEED = speed;
        MINIMUM_MANA = mana;
        MINIMUM_LUCK = luck;
        pointsLeft = 12;
        pointAmount.setText(pointsLeft.toString());
        textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));

        ObservableList<String> stats = statsView.getItems();
        stats.set(0, "Health: " + health);
        stats.set(1, "Attack: " + attack);
        stats.set(2, "Defense: " + defense);
        stats.set(3, "Speed: " + speed);
        stats.set(4, "Mana: " + mana);
        stats.set(5, "Luck: " + luck);
    }
}
