package io.github.haappi.views;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.*;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.HelloApplication;
import io.github.haappi.Storage;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TaskTracker {
    private final VBox animation = new VBox();
    public Label label;
    public VBox vbox;
    public View primary;
    @FXML
    private CharmListView<TaskObject, LocalDate> tasksListView;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("tasktracker.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return new View();
        }
    }

    public void taskCreateFlow(String taskName) {
        DatePicker datePicker = new DatePicker();
        LocalDate date = datePicker.showAndWait().orElseGet(LocalDate::now);

        TimePicker timePicker = new TimePicker();
        LocalTime time = timePicker.showAndWait().orElseGet(LocalTime::now).withSecond(0).withNano(0);

        TaskObject task = new TaskObject(taskName, date.toString(), time.toString());
        Storage.getInstance().appendTask(task);

        refresh();
    }

    private void finishTaskAnimation() {

        TranslateTransition slideDown = new TranslateTransition(Duration.seconds(2), vbox);
        slideDown.setByY(400);
        slideDown.setOnFinished(
                event -> {
                    FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), vbox);
                    fadeOut.setFromValue(1.0);
                    fadeOut.setToValue(0.0);
                    fadeOut.setOnFinished(
                            fadeEvent -> {
                                slideDown.setToY(-200);
                            });
                    fadeOut.play();
                });

        slideDown.play();
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

        //        tasksListView.setOnMouseClicked(event -> {
        //            System.out.println("a");
        //            System.out.println(event.getClickCount());
        //            if (event.getClickCount() >= 2) {
        //                finishTaskAnimation();
        //                TaskObject task = tasksListView.getSelectedItem();
        //                Storage.getInstance().removeTask(task);
        //                refresh();
        //            }
        //        });

        final FloatingActionButton floatingActionButton = new FloatingActionButton();
        floatingActionButton.setOnAction(
                e -> {
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitleText("Create a task");

                    VBox dialogContent = new VBox();
                    TextField inputField = new TextField();
                    Button saveButton = new Button("Save");

                    saveButton.setOnAction(
                            event -> {
                                dialog.setResult(inputField.getText());
                                dialog.hide();
                            });

                    dialogContent.getChildren().addAll(new Label("Task Name:"), inputField, saveButton);
                    dialog.setContent(dialogContent);

                    dialog.showAndWait().ifPresent(this::taskCreateFlow);
                });

        FloatingActionButton secondary =
                new FloatingActionButton(MaterialDesignIcon.REFRESH.text, e -> refresh());
        secondary.getStyleClass().add(FloatingActionButton.STYLE_CLASS_MINI);
        secondary.attachTo(floatingActionButton, Side.TOP);

        FloatingActionButton delete =
                new FloatingActionButton(
                        MaterialDesignIcon.DELETE.text,
                        e -> {
                            TaskObject task = tasksListView.getSelectedItem();
                            Storage.getInstance().removeTask(task);
                            refresh();
                        });

        delete.getStyleClass().add(FloatingActionButton.STYLE_CLASS_MINI);
        delete.show();
        delete.setFloatingActionButtonHandler(FloatingActionButton.BOTTOM_LEFT);
        floatingActionButton.showOn(primary);

        animation.getStyleClass().add("slide-down-vbox");
        animation.getChildren().add(new Label("Hooray. You finished a task!"));
        animation.setTranslateY(-200);

        primary.getChildren().add(animation);

        refresh();
    }

    public void refresh() {
        tasksListView.setComparator(
                (o1, o2) -> {
                    LocalDate o1Date = LocalDate.parse(o1.date);
                    LocalDate o2Date = LocalDate.parse(o2.date);
                    LocalTime o1Time = LocalTime.parse(o1.time);
                    LocalTime o2Time = LocalTime.parse(o2.time);
                    if (o1Date.isBefore(o2Date)) {
                        return -1;
                    } else if (o1Date.isAfter(o2Date)) {
                        return 1;
                    } else {
                        if (o1Time.isBefore(o2Time)) {
                            return -1;
                        } else if (o1Time.isAfter(o2Time)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });
        ArrayList<TaskObject> tasks = Storage.getInstance().getTasks();
        ObservableList<TaskObject> notes = FXCollections.observableArrayList(new ArrayList<>(tasks));
        tasksListView.setItems(notes);
    }

    public static class TaskObject {

        private final String name;
        private final String date;
        private final String time;
        private final boolean done;

        public TaskObject(String name, String dueDate, String dueTime, boolean done) {
            this.name = name;
            this.date = dueDate;
            this.time = dueTime;
            this.done = false;
        }

        public TaskObject(String name, String dueDate, String dueTime) {
            this(name, dueDate, dueTime, false);
        }

        public String toString() {
            return name + " " + date + " " + time + " " + (done ? "Done" : "Not Done");
        }

        public Map<String, String> asJson() {
            Map<String, String> json = new HashMap<>();
            json.put("name", name);
            json.put("date", date);
            json.put("time", time);
            json.put("done", String.valueOf(done));
            return json;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            final TaskObject other = (TaskObject) obj;
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            if (!Objects.equals(this.date, other.date)) {
                return false;
            }
            if (!Objects.equals(this.time, other.time)) {
                return false;
            }
            return this.done == other.done;
        }
    }
}
