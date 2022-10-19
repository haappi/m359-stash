package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.Player;
import io.github.haappi.battleGame.Classes.Weapons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.print.Book;
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
        storeItemss.add(new Weapons.WeaponBuilder("Iron Sword", 10.00).setDamage(10).build());
        storeItemss.add(new Weapons.WeaponBuilder("Wooden Sword", 5).setDamage(2).build());
        System.out.println(storeItemss);

    }

    @FXML protected void changeShoppingFor(ActionEvent actionEvent) {
    }


    @FXML protected void openInventory(ActionEvent actionEvent) {
    }

    @FXML protected void mainMenu(ActionEvent actionEvent) {
        HelloApplication.getInstance().setStageScene("main-menu");
    }

    @FXML protected void buyItem() {
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
            // fixme this still "buying" the item w/o subtracting money
            player.getInventory().add(item);
            storeItems.getItems().remove(item);
        }
    }
}
