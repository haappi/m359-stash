package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import io.github.haappi.battleGame.Classes.Potions;
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
        items.add(new Potions.PotionBuilder("Health Potion", 30.00).setAmountGiven(20.00).setStatGiven("health").build());
        items.add(new Potions.PotionBuilder("Attack Potion", 30.00).setAmountGiven(10.00).setStatGiven("attack").build());
        items.add(new Potions.PotionBuilder("Defense Potion", 30.00).setAmountGiven(15.00).setStatGiven("defense").build());
        items.add(new Potions.PotionBuilder("Speed Potion", 30.00).setAmountGiven(5.00).setStatGiven("speed").build());
//        items.add(new Potions.PotionBuilder("Mana Potion", 30.00).setAmountGiven(13.00).setStatGiven("mana").build());
        return items;
    }

    @FXML
    protected void initialize() {
        this.bankBal.setText(String.valueOf(HelloApplication.getInstance().getPlayer().getBankBalance()));
        List<InventoryItem> storeItemss = storeItems.getItems();
        storeItemss.addAll(StoreController.getStoreItems());
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

        if (item instanceof Potions potions) {
            Player player = HelloApplication.getInstance().getPlayer();
            if (player.getBankBalance() >= potions.getPrice()) {
                player.setBankBalance(player.getBankBalance() - potions.getPrice());
                player.getInventory().add(potions);
                bankBal.setText("Bank Balance: " + player.getBankBalance());
                recent.setText("You bought a " + potions.getName());
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
