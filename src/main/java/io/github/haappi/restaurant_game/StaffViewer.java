package io.github.haappi.restaurant_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class StaffViewer {
    public ListView<Staff> staffList;
    public Label staffInfor;

    @FXML
    protected void initialize() {
        staffList.getItems().addAll(HelloApplication.getInstance().getGameInstance().getCurrentBuilding().getStaff());
    }

    public void onClick(MouseEvent mouseEvent) {
        Staff current = staffList.getSelectionModel().getSelectedItem();
        if (current != null) {
            staffInfor.setText(current.staffInformation());
        }
    }

    public void trainStaff(ActionEvent actionEvent) {
        Staff current = staffList.getSelectionModel().getSelectedItem();
        if (current != null) {
            current.tryTraining();
        }
        staffInfor.setText(current.staffInformation());
    }

    public void prevMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("game-menu-view");
    }

    public void addStaff(ActionEvent actionEvent) {
        Staff current = staffList.getSelectionModel().getSelectedItem();
        if (current != null) {
            Game game = HelloApplication.getInstance().getGameInstance();
            game.getCurrentBuilding().getStaff().add(current);
            staffList.getItems().clear();
            staffList.getItems().addAll(game.getCurrentBuilding().getStaff());
            game.setMoney(game.getMoney() - 100);
        }
    }

    public void remvoeStaff(ActionEvent actionEvent) {
        Staff current = staffList.getSelectionModel().getSelectedItem();
        if (current != null) {
            HelloApplication.getInstance().getGameInstance().getCurrentBuilding().getStaff().remove(current);
            staffList.getItems().clear();
            staffList.getItems().addAll(HelloApplication.getInstance().getGameInstance().getCurrentBuilding().getStaff());
        }
    }
}
