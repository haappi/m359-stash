package io.github.haappi.views;

import com.gluonhq.charm.glisten.mvc.View;
import io.github.haappi.HelloApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TaskTracker {
    public ListView<TaskObject> listView;
    public Label label;
    public VBox vbox;
    public View primary;
    public TextField taskNameField;
    public DatePicker datePicker;
    private ObservableList<TaskObject> tasks;
    @FXML
    private ListView<TaskObject> tasksListView;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("tracker.fxml"));
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
            String sb = "{" +
                    "\"name\": \"" + name + "\", " +
                    "\"dueDate\": \"" + date + "\", " +
                    "\"completed\": " + done +
                    "}";
            return sb;
        }


    }

}
