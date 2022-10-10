package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.List;

public class StartMenuController {
    @FXML protected Text saveInformation;
    @FXML protected ListView<SaveInstance> saveFiles;

    @FXML
    public void initialize() {
        saveFiles.getItems().addAll(getSaveInstances());
    }

    private List<SaveInstance> getSaveInstances() {
        // todo get all the saves from the directory specified.
//        return null;
        return List.of(new SaveInstance("save1", "example save", "/usr/bin/ls", System.currentTimeMillis()));
    }

    @FXML
    protected void loadSaveFile(ActionEvent actionEvent) {
//        SaveInstance selectedItem = saveFiles.getSelectionModel().getSelectedItem();
//        if (selectedItem != null) {
//            saveInformation.setText(selectedItem.toString());
//        }
    }

    @FXML
    protected void onSelected(MouseEvent mouseEvent) {
        SaveInstance selectedItem = saveFiles.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            saveInformation.setText(selectedItem.getDetails());
        }
    }
}
