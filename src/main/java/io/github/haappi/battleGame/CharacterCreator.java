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

    @FXML
    protected void initialize() {
        pointAmount.setText(pointsLeft.toString());
        textPointAmount.setText(String.format("You have %s points remaining.", pointsLeft));
        statsView.getItems().add("Health: 20");
        statsView.getItems().add("Attack: 5");
        statsView.getItems().add("Defense: 5");
        statsView.getItems().add("Speed: 2");
        statsView.getItems().add("Mana: 10");
        statsView.getItems().add("Luck: 2");
        statsView.getSelectionModel().select(selectedIndex);
    }

    @FXML protected void increase(ActionEvent actionEvent) {
    }

    @FXML protected void decrease(ActionEvent actionEvent) {
    }

    @FXML protected void finished(ActionEvent actionEvent) {
    }

    @FXML protected void charNameMaker() {
        characterName = charNameMaker.getText() != null ? charNameMaker.getText() : "Timmy";
    }

    @FXML protected void onStatChange(MouseEvent mouseEvent) {
        ListView<String> source = (ListView<String>) mouseEvent.getSource();
        String string = source.getSelectionModel().getSelectedItem();
        System.out.println(string);
    }

}
