package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class StartMenuController {
    @FXML protected Text saveInformation;
    @FXML protected ListView<SaveInstance> saveFiles;

    @FXML
    protected void loadSaveFile(ActionEvent actionEvent) {
        SaveInstance selectedItem = saveFiles.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            saveInformation.setText(selectedItem.toString());
        }
    }
}
