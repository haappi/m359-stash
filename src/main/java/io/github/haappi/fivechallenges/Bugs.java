package io.github.haappi.fivechallenges;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static io.github.haappi.fivechallenges.Common.loadFMXLView;

public class Bugs {
    @FXML
    protected Text bugCount;
    private int buggyCounter = 2;
    private boolean takingInput = true;
    @FXML
    protected Text currentGen;
    private int currentGeneration = 1;

    public static void run(Stage stage) {
        loadFMXLView(stage, "bugs.fxml");
    }

    @FXML
    protected void breed() {
        if (!takingInput) {
            return;
        }
        breedBugs();
        incrementGeneration();
    }

    private void breedBugs() {
        if (buggyCounter < 2) {
            bugCount.setText("The bugs went extinct");
            takingInput = false;
        }
        buggyCounter *= 2;
        getBugs();
    }

    private void incrementGeneration() {
        currentGeneration++;
        currentGen.setText(getGenerationMessage(currentGeneration));
    }

    private String getBugMessage(int counter) {
        if (buggyCounter < 2) {
            takingInput = false;
            return "The bugs went extinct";
        }
        return "Current Roach Count: " + counter;
    }

    private String getGenerationMessage(int counter) {
        return "Current Generation: " + counter;
    }

    @FXML
    protected void spray() {
        if (!takingInput) {
            return;
        }
        double randomPercentage = Math.random();
        buggyCounter = (int) (buggyCounter * randomPercentage);
        getBugs();
        incrementGeneration();
    }

    @FXML
    protected void getBugs() {
        if (buggyCounter < 2) {
            bugCount.setText("The bugs went extinct");
            takingInput = false;
        }
        bugCount.setText(getBugMessage(buggyCounter));
    }
}
