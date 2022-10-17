package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Weapons;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.List;

public class StoreController {
    public Text recent;
    public Text bankBal;
    @FXML
    protected ListView<InventoryItem> storeItems;
    @FXML
    protected Text currentlyShopping;

    @FXML
    protected void initialize() {
        List<InventoryItem> storeItemss = storeItems.getItems();
        storeItemss.add(new Weapons("Sword"));
    }
}
