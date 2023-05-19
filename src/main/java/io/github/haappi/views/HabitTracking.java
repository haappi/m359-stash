package io.github.haappi.views;

import com.gluonhq.charm.glisten.application.AppManager;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import io.github.haappi.Config;
import io.github.haappi.HelloApplication;
import io.github.haappi.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HabitTracking {
    private final ArrayList<HabitTask> habits = new ArrayList<>();
    public ListView<HabitTask> listView;
    public VBox vbox;
    public View primary;
    private CheckBox[] checkBoxes;

    public static View load() {
        try {
            return FXMLLoader.load(HelloApplication.class.getResource("habittracking.fxml"));
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }

    @FXML
    private void initialize() {
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


        checkBoxes = new CheckBox[7];
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < 7; i++) {
            checkBoxes[i] = new CheckBox(daysOfWeek[i]);
        }


        final FloatingActionButton floatingActionButton = new FloatingActionButton();
        floatingActionButton.setOnAction(
                e -> {
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setTitleText("Create a habit");

                    VBox dialogContent = new VBox();
                    TextField inputField = new TextField();
                    Button saveButton = new Button("Save");

                    saveButton.setOnAction(
                            event -> {
                                dialog.setResult(inputField.getText());
                                dialog.hide();
                            });

                    dialogContent.getChildren().addAll(new Label("Habit Name:"), inputField, saveButton);
                    dialog.setContent(dialogContent);

                    dialog.showAndWait().ifPresent(habit -> {
                        HabitTask habitt = new HabitTask(habit);
                        Storage.getInstance().appendHabit(habitt);
                    });
                });

        FloatingActionButton secondary =
                new FloatingActionButton(MaterialDesignIcon.REFRESH.text, e -> reload());
        secondary.getStyleClass().add(FloatingActionButton.STYLE_CLASS_MINI);
        secondary.attachTo(floatingActionButton, Side.TOP);

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() > 2) {
                listView.getFocusModel().getFocusedItem().increment();
                Storage.getInstance().setHabits(Config.getInstance().getDisplayName(), new ArrayList<>(listView.getItems()));
            }
        });


    }

    private void reload() {
        this.habits.clear();
        this.habits.addAll(Storage.getInstance().getHabits());
        this.listView.getItems().clear();
        this.listView.getItems().addAll(this.habits);
    }

    public static class HabitTask {

        private final String habitName;
        private int timesDone;

        public HabitTask(String habitName) {
            this.habitName = habitName;
            timesDone = 0;
        }

        public HabitTask(String habitName, int count) {
            this.habitName = habitName;
            timesDone = count;
        }

        public int increment() {
            this.timesDone++;
            return this.timesDone;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            final HabitTask other = (HabitTask) obj;
            return Objects.equals(this.habitName, other.habitName);
        }

        public String toString() {
            return this.habitName;
        }
    }

}
