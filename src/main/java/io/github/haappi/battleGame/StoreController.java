package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import io.github.haappi.battleGame.Classes.Weapons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class StoreController {
    private static final ArrayList<InventoryItem> items = new ArrayList<>(StoreController.getStoreItems());
    @FXML
    protected Text itemInformation;
    @FXML
    protected Text recent;
    @FXML
    protected Text bankBal;
    @FXML
    protected MenuButton shoppingForWhat;
    @FXML
    protected ListView<InventoryItem> storeItems;
    @FXML
    protected Text currentlyShopping;

    private static List<InventoryItem> getStoreItems() {
        ArrayList<InventoryItem> items = new ArrayList<>();
        items.add(new Weapons.WeaponBuilder("Wooden Sword", 5).setDamage(2).build());
        items.add(new Weapons.WeaponBuilder("Iron Sword", 10).setDamage(4).build());
        items.add(new Weapons.WeaponBuilder("Steel Sword", 15).setDamage(6).build());
        items.add(new Weapons.WeaponBuilder("Diamond Sword", 20).setDamage(8).build());
        return items;
    }

    @FXML
    protected void initialize() {
        List<InventoryItem> storeItemss = storeItems.getItems();
        storeItemss.add(new Weapons.WeaponBuilder("Iron Sword", 10.00).setDamage(10).build());
        storeItemss.add(new Weapons.WeaponBuilder("Wooden Sword", 5).setDamage(2).build());
    }

    @FXML
    protected void changeShoppingFor(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        List<InventoryItem> storeItemss = storeItems.getItems();
        storeItemss.clear();
        String type = menuItem.getId().toLowerCase();
        for (InventoryItem item : StoreController.items) {
//            System.out.println(shoppingForWhat.getSel);
        }
    }


    @FXML
    protected void openInventory() {
        InventoryView.previousClassFXML = "store-view.fxml";
        HelloApplication.getInstance().setStageScene("inventory-view.fxml");
    }

    @FXML
    protected void mainMenu() {
        HelloApplication.getInstance().setStageScene("main-menu");
    }

    @FXML
    protected void buyItem() {
        InventoryItem item = storeItems.getSelectionModel().getSelectedItem();
        if (item == null) {
            return;
        }
        if (item instanceof Weapons weapon) {
            Player player = HelloApplication.getInstance().getPlayer();
            if (player.getBankBalance() >= weapon.getPrice()) {
                player.setBankBalance(player.getBankBalance() - weapon.getPrice());
                player.getInventory().add(weapon);
                bankBal.setText("Bank Balance: " + player.getBankBalance());
                recent.setText("You bought a " + weapon.getName());
            } else {
                recent.setText("You don't have enough money to buy this item");
            }
        }
    }

    public void viewItemInformation(MouseEvent mouseEvent) {
        ListView<InventoryItem> source = (ListView<InventoryItem>) mouseEvent.getSource();
        InventoryItem selectedItem = source.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem instanceof Weapons weapon) {
                itemInformation.setText(weapon.getInformation());
            }
        }
    }
}
