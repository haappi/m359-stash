package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class InventoryView {
    @FXML protected static String previousClassFXML = "main-menu.fxml";
    @FXML protected Text itemInformation;
    @FXML protected ListView<InventoryItem> inventoryItems;

    @FXML
    public void initialize() {
        if (HelloApplication.getInstance().getPlayer() != null) {
            inventoryItems.getItems().addAll(HelloApplication.getInstance().getPlayer().getInventory());
        }
    }

    @FXML protected void sortButton(ActionEvent actionEvent) {
        // todo sort the inventory items by the type specified
    }

    @FXML protected void goBackToOldMenu() {
        HelloApplication.getInstance().setStageScene(previousClassFXML);
    }

    @FXML protected void onItemHover(MouseEvent mouseEvent) {
        InventoryItem item = inventoryItems.getSelectionModel().getSelectedItem();
        if (item == null) {
            return;
        }
        itemInformation.setText(item.toString());
    }
}
