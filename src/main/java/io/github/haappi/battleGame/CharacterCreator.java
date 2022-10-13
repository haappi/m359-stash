package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.print.Book;

public class CharacterCreator {
    @FXML protected TextField charNameMaker;
    @FXML protected Text basicInformation;
    @FXML protected MenuButton changePlayerType;
    @FXML protected ListView<String> statsView;
    private Integer selectedIndex = 0;
    private Integer pointsLeft = 12;
    private String characterName = "Timmy";
    @FXML
    protected Text textPointAmount;
    @FXML protected Text pointAmount;

    private final int MINIMUM_HEALTH = 20;
    private final int MINIMUM_ATTACK = 5;
    private final int MINIMUM_DEFENSE = 5;
    private final int MINIMUM_SPEED = 2;
    private final int MINIMUM_MANA = 10;
    private final int MINIMUM_LUCK = 2;

    private String selectedStat = "Health";


    @FXML
    protected void initialize() {
        pointAmount.setText(pointsLeft.toString());
        textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft)); // todo fix this overlapping weirdly
        // prolly just change the final x position of it
        statsView.getItems().add("Health: " + MINIMUM_HEALTH);
        statsView.getItems().add("Attack: " + MINIMUM_ATTACK);
        statsView.getItems().add("Defense: " + MINIMUM_DEFENSE);
        statsView.getItems().add("Speed: " + MINIMUM_SPEED);
        statsView.getItems().add("Mana: " + MINIMUM_MANA);
        statsView.getItems().add("Luck: " + MINIMUM_LUCK);
        statsView.getSelectionModel().select(selectedIndex);
    }

    @FXML protected void increase(ActionEvent actionEvent) {
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

    @FXML protected void decrease(ActionEvent actionEvent) {
        switch (selectedStat.toLowerCase()) {
            case "health" -> { // todo refactor this later to use a common method
                    if (MINIMUM_HEALTH < Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1])) {
                    statsView.getItems().set(selectedIndex, "Health: " + (Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]) - 1));
                    pointsLeft++;
                    pointAmount.setText(pointsLeft.toString());
                    textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
                }
            }
            case "attack" -> {
                if (MINIMUM_ATTACK < Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1])) {
                    statsView.getItems().set(selectedIndex, "Attack: " + (Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]) - 1));
                    pointsLeft++;
                    pointAmount.setText(pointsLeft.toString());
                    textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
                }
            }
            case "defense" -> {
                if (MINIMUM_DEFENSE < Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1])) {
                    statsView.getItems().set(selectedIndex, "Defense: " + (Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]) - 1));
                    pointsLeft++;
                    pointAmount.setText(pointsLeft.toString());
                    textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
                }
            }
            case "speed" -> {
                if (MINIMUM_SPEED < Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1])) {
                    statsView.getItems().set(selectedIndex, "Speed: " + (Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]) - 1));
                    pointsLeft++;
                    pointAmount.setText(pointsLeft.toString());
                    textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
                }
            }
            case "mana" -> {
                if (MINIMUM_MANA < Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1])) {
                    statsView.getItems().set(selectedIndex, "Mana: " + (Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]) - 1));
                    pointsLeft++;
                    pointAmount.setText(pointsLeft.toString());
                    textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
                }
            }
            case "luck" -> {
                if (MINIMUM_LUCK < Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1])) {
                    statsView.getItems().set(selectedIndex, "Luck: " + (Integer.parseInt(statsView.getItems().get(selectedIndex).split(": ")[1]) - 1));
                    pointsLeft++;
                    pointAmount.setText(pointsLeft.toString());
                    textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
                }
            }
        }

    }

    @FXML protected void finished(ActionEvent actionEvent) {
    }

    @FXML protected void charNameMaker() {
        characterName = charNameMaker.getText() != null ? charNameMaker.getText() : "Timmy";
    }

    @FXML protected void onStatChange(MouseEvent mouseEvent) {
        ListView<?> source = (ListView<?>) mouseEvent.getSource();
        String string = (String) source.getSelectionModel().getSelectedItem();
        selectedStat = string.split(":")[0];
        selectedIndex = source.getSelectionModel().getSelectedIndex();
    }
    // todo add the change class type thing and have final hardcoded stats // buffs and nerdfs for ther class

}
