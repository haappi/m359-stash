package io.github.haappi.views;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.Config;
import io.github.haappi.HelloApplication;
import io.github.haappi.Storage;
import io.github.haappi.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TaskTracker {
    public Label label;
    public VBox vbox;
    public View primary;
    public TextField taskNameField;
    public DatePicker datePicker;
    private final ObservableList<TaskObject> tasks = javafx.collections.FXCollections.observableArrayList();
    @FXML
    private ListView<TaskObject> tasksListView;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("tasktracker.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return new View();
        }
    }

    public void createTask() {
        String taskName = taskNameField.getText();
        if (!taskName.isEmpty() && datePicker.getValue() != null) {
            String dueDate = datePicker.getValue().toString();
            TaskObject task = new TaskObject(taskName, dueDate);
            tasks.add(task);
            taskNameField.clear();
            datePicker.setValue(null);
            Storage.getInstance().appendTask(task);
        }


        tasksListView.setPrefHeight(200);
        tasksListView.setItems(tasks);

    }

    public ObservableList<TaskObject> getTasks() {
        return tasks;
    }

    public String tasksToString() {
        StringBuilder sb = new StringBuilder();
        for (TaskObject task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }


    public void initialize() {
        primary
                .showingProperty()
                .addListener(
                        (obs, oldValue, newValue) -> {
                            if (newValue) {
                                AppBar appBar = AppManager.getInstance().getAppBar();
                                appBar.setNavIcon(
                                        MaterialDesignIcon.MENU.button(
                                                e -> AppManager.getInstance().getDrawer().open()));
                                appBar.setTitleText("Primary");
                            }
                        });

        tasksListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TaskObject task = tasksListView.getSelectionModel().getSelectedItem();
                tasks.remove(task);
                Storage.getInstance().removeTask(task);
            }
        });
    }

    public void refresh(ActionEvent actionEvent) {
        tasks.clear();
        tasks.addAll(Storage.getInstance().getTasks());
    }

    public static class TaskObject {

        private final String name;
        private final String date;
        private final boolean done;

        public TaskObject(String name, String dueDate) {
            this.name = name;
            this.date = dueDate;
            this.done = false;
        }

        public String toString() {
            return name + " " + date;
        }

        public Map<String, String> asJson() {
            Map<String, String> json = new HashMap<>();
            json.put("name", name);
            json.put("date", date);
            json.put("done", String.valueOf(done));
            return json;
        }


    }

}
