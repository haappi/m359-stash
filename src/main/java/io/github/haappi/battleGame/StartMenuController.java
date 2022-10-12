package io.github.haappi.battleGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StartMenuController {
    @FXML
    protected Text saveInformation;
    @FXML
    protected ListView<SaveInstance> saveFiles;

    @FXML
    public void initialize() throws IOException {
        saveFiles.getItems().add(new SaveInstance());
        // todo make loading logic
//        saveFiles.getItems().addAll(getSaveInstances());
    }

    private List<SaveInstance> getSaveInstances() throws IOException {
        File directory = new File("save-files");
        System.out.println(directory.getAbsolutePath());
        if (!directory.exists()) {
            directory.mkdirs(); // create the directory if it doesn't exist
        }
        File[] files = directory.listFiles();
        ArrayList<SaveInstance> saveInstances = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    String fileRead = Utils.listToString(Files.readAllLines(file.toPath()));
                    saveInstances.add(new SaveInstance(fileRead, file.getPath(), file.lastModified()));
                }
            }
        }

        while (saveInstances.size() != 3) {
            saveInstances.add(new SaveInstance());
        }
        return saveInstances;
    }

    @FXML
    protected void loadSaveFile(ActionEvent actionEvent) {
        SaveInstance selectedItem = saveFiles.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            saveInformation.setText("Loading " + selectedItem + "!");
        } else {
            saveInformation.setText("Select a save file.");
        }
    }

    private void saveFileLoader(SaveInstance saveFile) {
        if (saveFile.getPath().equals("new-save-file")) {
            // it's a new save file
        } else {
            // read the json in and construct the classes that way
        }
    }

    @FXML
    protected void onSelected(MouseEvent mouseEvent) {
        SaveInstance selectedItem = saveFiles.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            saveInformation.setText(selectedItem.getDetails());
        }
    }
}
