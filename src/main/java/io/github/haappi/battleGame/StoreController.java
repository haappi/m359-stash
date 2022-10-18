package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Weapons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;

import java.util.List;

public class StoreController {
    @FXML protected Text recent;
    @FXML protected Text bankBal;
    @FXML protected MenuButton shoppingForWhat;
    @FXML
    protected ListView<InventoryItem> storeItems;
    @FXML
    protected Text currentlyShopping;

    @FXML
    protected void initialize() {
        List<InventoryItem> storeItemss = storeItems.getItems();
        storeItemss.add(new Weapons("Sword"));
    }

    @FXML protected void changeShoppingFor(ActionEvent actionEvent) {
    }

    @FXML protected void buyItem(ActionEvent actionEvent) {
    }

    @FXML protected void openInventory(ActionEvent actionEvent) {
    }

    @FXML protected void mainMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("main-menu");
    }
}
