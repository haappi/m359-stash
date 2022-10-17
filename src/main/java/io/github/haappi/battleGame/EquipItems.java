package io.github.haappi.battleGame;

import io.github.haappi.battleGame.Classes.HoldableItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class EquipItems {
    private final ArrayList<HoldableItem> pickedItems = new ArrayList<>();
    @FXML
    protected ListView<HoldableItem> inventoryItems;
    @FXML protected ListView<HoldableItem> selectedItems;


    @FXML protected void onItemAdded(MouseEvent mouseEvent) {
    }

    @FXML protected void onItemRemoved(MouseEvent mouseEvent) {
    }
}
